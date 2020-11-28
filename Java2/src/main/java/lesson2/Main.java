//1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
//подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и
//просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
//ячейке лежит символ или текст вместо числа), должно быть брошено исключение
//MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
//3. В методе main() вызвать полученный метод, обработать возможные исключения
//MyArraySizeException и MyArrayDataException и вывести результат расчета.

package lesson2;

public class Main {

    public static int arrayStrToInt(String[][] myArray) {

        int sum = 0;

        if (myArray.length != 4)
            throw new MyArraySizeException("Количество строк != 4");

        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i].length != 4) throw new MyArraySizeException("Количество столбцов в строке " + i + " ! = 4");
        }

        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(myArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(e.toString() + ". Неверные данны в ячейке [" + i + "]["+ j + "]");
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        String[][] myArray = new String[4][4];

        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                myArray[i][j] = Integer.toString((int) (Math.random() * 10));
            }
        }

        myArray[1][2] = "G";

        for (String[] strings : myArray) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }

        try {
            System.out.println(arrayStrToInt(myArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }
}
