//1 Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и
//вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//Посчитать, сколько раз встречается каждое слово.
//2 Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и
//телефонных номеров. В этот телефонный справочник с помощью метода add() можно
//добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует
//учесть, что под одной фамилией может быть несколько телефонов (в случае
//однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
//Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
//взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода
//результатов проверки телефонного справочника.

package lesson3;

import java.util.*;

public class Main {

    public static void getCountElements (String[] array) {

        System.out.println(Arrays.toString(array));

        HashMap<String, Integer> hm = new HashMap<>();

        for (String s : array) {
            hm.put(s, hm.getOrDefault(s, 0) + 1);
        }

        for (Map.Entry<String, Integer> o : hm.entrySet()) {
            System.out.println(o.getKey() + " : " + o.getValue());
        }
    }

    public static void main(String[] args) {

        String[] array = {"Java", "Bash", "Fortran", "Kotlin", "Lua", "Python", "SQL",
                "Java", "Kotlin", "Python", "Java", "Kotlin", "Java", "Java",
                "SQL", "Java", "Fortran", "Bash", "Python"};

        getCountElements (array);

        System.out.println();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Phonebook phoneBook = new Phonebook("phonebook.csv", ";");

        phoneBook.printPhoneBook();

        System.out.println("\n" + phoneBook.get("Ivanov") + "\n");

        phoneBook.add("999999999999","newName");
        phoneBook.printPhoneBook();
    }
}
