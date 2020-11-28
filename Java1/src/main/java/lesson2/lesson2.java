package lesson2;

import java.util.Arrays;

public class lesson2 {
    public static void main(String[] args) {

        arrayOf01();
        arrayLength8();
        arrayDouble();
        arraySquare();
        arrayMinMax();

        int[] arr = {2, 2, 2, 1, 2, 2, 10, 1}; //Для теста [1, 1, 1, 2, 1] true, [1, 10, 1, 2, 1] false
        System.out.println(checkBalance(arr));

        arrayShift(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
        //arrayShift(new int[]{1, 2, 3, 4, 5, 6}, -8);

    }

    //Вывод одномерного массива - перегружаем метод
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.printf("%3d", i);
        }
        System.out.println();
    }

    //Вывод двумерного массива - перегружаем метод
    public static void printArray(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void arrayOf01() {
        System.out.println("Задание 1: ");
        int[] arr = new int[10];

        //Заполнение массива 0 и 1
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        printArray(arr);

        //Инверсия массива с уловием по заданию
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
        printArray(arr);

        //Альтернитивное решение - побитовый сдвиг первого бита, но увы в задании - "С помощью цикла и УСЛОВИЯ"
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ~arr[i] & 1;
        }
        printArray(arr);
    }

    //2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    public static void arrayLength8() {
        System.out.println("Задание 2: ");
        int[] arr = new int[8];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        printArray(arr);
    }

    //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void arrayDouble() {
        System.out.println("Задание 3: ");
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(arr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
        printArray(arr);
    }

    //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    //и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void arraySquare() {
        System.out.println("Задание 4: ");
        int size = 10;
        int[][] arr = new int[size][size];

        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[i][arr.length - i - 1] = 1;
        }
        printArray(arr);
    }

    //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    public static void arrayMinMax() {
        System.out.println("Задание 5: ");
        int[] arr = new int[15];

        //Заполнение массива
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        printArray(arr);

        int max = arr[0], min = arr[0];
        //Поиск минимального и максимального элементов массива
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }

        System.out.println("Min=" + min + " Max=" + max);

        //Альтернатива, вопрос лишь при таком подходе дважды происходит скан массива? Т.е. как отразится на времени
        //выполнение при очень больших массивах
        System.out.println("Min=" + (Arrays.stream(arr).min()).getAsInt() + " Max=" + Arrays.stream(arr).max().getAsInt());
    }

    //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
    // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    // checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    // граница показана символами ||, эти символы в массив не входят.
    public static boolean checkBalance(int[] arr) {
        System.out.println("Задание 6:");
        printArray(arr);

        int sumLeft, sumRight;

        for (int i = 1; i < arr.length; i++) {

            sumLeft = sumRight = 0;

            //Считаем сумму слева
            for (int j = 0; j <= i - 1; j++) {
                sumLeft += arr[j];
            }

            //Считаем сумму справа
            for (int j = i; j < arr.length; j++) {
                sumRight += arr[j];
            }

            //Форматируем вывод
            if (sumLeft == sumRight) {
                for (int k = 0; k < arr.length; k++) {
                    System.out.printf("%3d", arr[k]);
                    if (k == i - 1) {
                        System.out.print(" ||");
                    }
                }
                System.out.println();
                return true;
            }
        }
        return false;
    }

    //7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или
    // отрицательным), при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи
    // нельзя пользоваться вспомогательными массивами.
    public static void arrayShift(int[] arr, int pos) {
        System.out.println("Задание 7: ");
        printArray(arr);

        //Если количество сдвигов больше размера массива то берем мод. При этом сдвинуть на -pos тоже самое что
        //сдвинуть на (длина массива минус pos), дабы не писать лишний код для отрицательного числа
        pos = (pos > 0) ? pos % arr.length : arr.length + pos % arr.length;

        //Подход №1 рабочий и короткий, но не нравится что мы выполняем сдвиг массива (pos * (arr.length-1)) раз
        System.out.print("Подход №1 ");
        for (int j = 0; j < pos; j++) {
            int buffer = arr[0];

            //Делаем сдвиг на один элемент
            for (int i = 0; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[arr.length - 1] = buffer;
        }
        printArray(arr);
        //Конец Подход №1

        //Подход №2. https://codelab.ru/t/cycle_shift/
        System.out.print("Подход №2 ");
        int gcdVal = gcd(arr.length, pos);

        for (int i = 0; i < gcdVal; i++) {
            int buffer = arr[i];
            int tmpVar = i;
            while (true) {
                int idx = tmpVar + pos;

                //Если вышли за длину массива то идем от начала
                if (idx >= arr.length) {
                    idx -= arr.length;
                }

                if (idx == i) {
                    break;
                }

                arr[tmpVar] = arr[idx];
                tmpVar = idx;
                arr[tmpVar] = buffer;
            }
        }
        printArray(arr);
        //Конец Подход №2.
    }

    //Вычисление наибольшего общего остатка
    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmpVar = a % b;
            a = b;
            b = tmpVar;
        }
        return a;
    }
}