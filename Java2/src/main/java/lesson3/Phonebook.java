package lesson3;

import java.util.*;

public class Phonebook {

    public TreeMap<String, String> phoneBook = new TreeMap<>();

    public Phonebook(String path, String delimeter) {
        Scanner in = new Scanner(Main.class.getResourceAsStream("/" + path));

        while (in.hasNextLine()) {
            String[] buffer = in.nextLine().split(delimeter);
            if (buffer.length == 2)
                this.phoneBook.put(buffer[0], buffer[1]);
        }
    }

    public TreeMap<String, String> get(String name) {
        TreeMap<String, String> foundPhones = new TreeMap<>();
        for (Map.Entry<String, String> o : phoneBook.entrySet()) {
            if (o.getValue().equals(name)) foundPhones.put(o.getKey(), o.getValue());
        }
        return foundPhones;
    }

    public void add (String phone, String name){
        phoneBook.put(phone, name);
    }

    public void printPhoneBook() {
        for (Map.Entry<String, String> pb : phoneBook.entrySet())
            System.out.println(pb.getKey() + " : " + pb.getValue());
    }

}
