package Lesson7;

import Lesson7.Ints.AfterSuite;
import Lesson7.Ints.BeforeSuite;
import Lesson7.Ints.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class TestsHandler {
    private static Object obj;

    public static void start(Class clazz) {
        if (!checkBeforeSuiteCount(clazz)) {
            throw new RuntimeException();
        }

        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Method beforeSuite = null;
        Method afterSuite = null;
        ArrayList<Method> methods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeSuite = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                afterSuite = method;
            } else if (method.getAnnotation(Test.class) != null) {
                methods.add(method);
            }
        }

        methods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        if (beforeSuite != null) {
            methods.add(0, beforeSuite);
        }

        if (afterSuite != null) {
            methods.add(afterSuite);
        }

        try {
            for (Method testMethod : methods) {
                if (Modifier.isPrivate(testMethod.getModifiers())) {
                    testMethod.setAccessible(true);
                }
                testMethod.invoke(obj);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkBeforeSuiteCount(Class clazz) {
        int beforeSuiteCnt = 0;
        int afterSuiteCnt = 0;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeSuiteCnt++;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                afterSuiteCnt++;
            }
        }

        return (beforeSuiteCnt < 2) && (afterSuiteCnt < 2);
    }
}
