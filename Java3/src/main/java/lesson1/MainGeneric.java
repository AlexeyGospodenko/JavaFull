package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class MainGeneric {

    //1. Написать метод, который меняет два элемента массива местами (массив может быть любого
    //ссылочного типа);
    public static <T> void swapElems(T[] array, int firstPos, int secondPos) {
        T buffer = array[firstPos];
        array[firstPos] = array[secondPos];
        array[secondPos] = buffer;

    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList<T> toArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static void main(String[] args) {

        //1.
        String[] strArr = {"Hello", " ", "World", " ", "!"};
        System.out.println(Arrays.toString(strArr));
        swapElems(strArr, 0, 2);
        System.out.println(Arrays.toString(strArr));

        Integer[] intArr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(intArr));
        swapElems(intArr, 0, 2);
        System.out.println(Arrays.toString(intArr));

        //2.
        //ArrayList<String> listString =
        ArrayList<String> listString = toArrayList(strArr);
        System.out.println(listString.getClass() + ": " + listString);


    /* 3. Задача:
    a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
    b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по
      типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
    c. Для хранения фруктов внутри коробки можно использовать ArrayList;
    d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта
      и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
    e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую
      коробку с той, которую подадут в compare() в качестве параметра. true – если их массы
      равны, false в противоположном случае. Можно сравнивать коробки с яблоками и
      апельсинами;
    f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
      Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
      Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
      объекты, которые были в первой;
    g. Не забываем про метод добавления фрукта в коробку */

        Box<Orange> orangeBox = new Box();
        for (int i = 0; i < 10; i++) {
            orangeBox.addFruit(new Orange());
        }

        Box<Apple> appleBox1 = new Box();
        for (int i = 0; i < 10; i++) {
            appleBox1.addFruit(new Apple());
        }

        Box<Apple> appleBox2 = new Box();
        for (int i = 0; i < 5; i++) {
            appleBox2.addFruit(new Apple());
        }

        System.out.println("\nВес коробки с яблоками 1: " + appleBox1.getWeight());
        System.out.println("Вес коробки с яблоками 2: " + appleBox2.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());
        System.out.println("Вес orangeBox = вес appleBox1: " + orangeBox.compare(appleBox1));

        appleBox2.moveTo(appleBox1);
        System.out.println("\nВес коробки с яблоками 1: " + appleBox1.getWeight());
        System.out.println("Вес коробки с яблоками 2: " + appleBox2.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());
        System.out.println("Вес orangeBox = вес appleBox1: " + orangeBox.compare(appleBox1));

    }
}
