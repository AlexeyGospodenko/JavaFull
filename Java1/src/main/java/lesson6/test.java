package lesson6;

public class test {
    public static void main(String[] args) {

        Cat cat = new Cat("Cat");
        System.out.println("\n" + cat.toString());
        cat.jump(2f);
        cat.run(200);

        Dog dog = new Dog("Dog");
        System.out.println("\n" + dog.toString());
        dog.jump(0.5f);
        dog.run(500);
        dog.swim(10);

    }

}
