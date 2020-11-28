package lesson1;

public class lesson1 {

    public static void main(String[] args) {

        //1. Создать пустой проект в IntelliJ IDEA и прописать метод main();
        System.out.println ("1. psvm");

        //2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte b = -127;
        short s = -32768;
        int i = -2147483648;
        long l = -92_233_720_368_54_775_808L;
        float f = 1.4e-45f;
        double d = 1.7e+308;
        boolean bool = true;
        char c = '\\';

        System.out.println("2. byte b = " + b);
        System.out.println("short s  = " + s);
        System.out.println("int i  = " + i);
        System.out.println("long l = " + l);
        System.out.println("float f = " + f);
        System.out.println("double d = " + d);
        System.out.println("boolean bool = " + bool);
        System.out.println("char c = " + c);

        System.out.println("3. a * (b + (c / d)) = " + calculate (3, 1, 4, 2));

        System.out.println("4. 10 <= a + b <= 20: " + isRange (6, 8));

        printSign (-3);

        System.out.println("6. Number a is negative: " + isNegative(-5));

        printHelloName("User");

        isLeap(100);

    }

    //3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    // где a, b, c, d – входные параметры этого метода;
    public static float calculate (float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    //4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах
    // от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    public static boolean isRange (int a, int b) {
        if ((a + b >= 10) && (a + b <= 20)) {
            return true;
        } else {
            return false;
        }
    }

    // 5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль
    // положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
    public static void printSign (int a) {
        System.out.print("5. Число a: ");
        if (a >= 0 ) {
            System.out.println("положительное");
        }
        else {
            System.out.println("отрицательное");
        }
    }

    //6. Написать метод, которому в качестве параметра передается целое число,
    // метод должен вернуть true, если число отрицательное
    public static boolean isNegative (int a) {
        if (a < 0) {
            return true;
        }
        return false;
    }

    // 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    // метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    public static void printHelloName(String name) {
        System.out.println("7. Привет, " + name + "!");
    }

    // 8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static void isLeap (int year) {
        System.out.print("8. Год " + year + ": ");
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println("високосный");
        } else {
            System.out.println("не високосный");
        }
    }

}