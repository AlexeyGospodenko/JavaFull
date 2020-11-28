package lesson6;

public class Cat extends Animal {

    public Cat(String name) {
        super(200, 2, name);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "maxDistance=" + maxDistance +
                ", maxHeight=" + maxHeight +
                ", name='" + name + '\'' +
                '}';
    }

}
