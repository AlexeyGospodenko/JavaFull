package Lesson7;

import Lesson7.Ints.AfterSuite;
import Lesson7.Ints.BeforeSuite;
import Lesson7.Ints.Test;

public class TestClass {

    @Test(priority = 7)
    public void testMethod1() {
        System.out.println("method priority = 7");
    }

    @Test(priority = 1)
    public void testMethod2() {
        System.out.println("method priority = 1");
    }

    @Test(priority = 3)
    private void testMethod4() {
        System.out.println("method priority = 3");
    }

    @Test
    public void testMethod5() {
        System.out.println("method priority = default");
    }

    @AfterSuite
    public void afterMethod() {
        System.out.println("method AfterSuite");
    }

    @BeforeSuite
    public void beforeSuiteMethod() {
        System.out.println("method BeforeSuite");
    }

}