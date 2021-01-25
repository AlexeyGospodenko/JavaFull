package Lesson6;

import java.util.Arrays;

public class ArrayUtils {

//Написать метод, которому в качестве аргумента передается не пустой одномерный
//целочисленный массив. Метод должен вернуть новый массив, который получен путем
//вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
//массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
//RuntimeException.
//Написать набор тестов для этого метода (по 3-4 варианта входных данных).
//Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ]

    public int[] arrayAfterLastFour(int[] array) {
        int pos = -1;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            return Arrays.copyOfRange(array, pos + 1, array.length);
        } else throw new RuntimeException("No found \"4\" in array");
    }

//Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной
//четверки или единицы, то метод вернет false; Написать набор тестов для этого метода (по 3-4
//варианта входных данных).
//[ 1 1 1 4 4 1 4 4 ] -> true
//[ 1 1 1 1 1 1 ] -> false
//[ 4 4 4 4 ] -> false
//[ 1 4 4 1 1 4 3 ] -> false

    public boolean checkArrayOneFour(int[] array) {
        boolean oneExists = false, fourExists = false;

        for (int i : array) {
            if (i != 1 && i != 4) {
                return false;
            }
            if (i == 1 && !oneExists) {
                oneExists = true;
            }
            if (i == 4 && !fourExists) {
                fourExists = true;
            }
        }
        return oneExists && fourExists;
    }


}
