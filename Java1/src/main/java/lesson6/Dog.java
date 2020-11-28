package lesson6;

public class Dog extends Animal {

    private int maxDistanceSwim;

    public Dog(String name) {
        super(500, 0.5f, name);
        this.maxDistanceSwim = (int) ((10 * 0.8) + (Math.random() * 10 * 0.4 + 1));
    }

    public void swim(int distanceSwim) {
        System.out.println(name + ".swim -> " + (distanceSwim <= maxDistanceSwim));
    }

    public int getMaxDistanceSwim() {
        return maxDistanceSwim;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "maxDistanceSwim=" + maxDistanceSwim +
                ", maxDistance=" + maxDistance +
                ", maxHeight=" + maxHeight +
                ", name='" + name + '\'' +
                '}';
    }
}
