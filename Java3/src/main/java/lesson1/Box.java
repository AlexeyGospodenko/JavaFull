package lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private final ArrayList<T> arrayList = new ArrayList<>();

    public void addFruit(T fruit) {
        arrayList.add(fruit);
    }

    public float getWeight() {
        if (arrayList.isEmpty()) {
            return 0;
        } else return arrayList.size() * arrayList.get(0).getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return (this.getWeight() == box.getWeight());
    }

    public void moveTo (Box<T> box) {
        box.getArrayList().addAll(this.arrayList);
        this.arrayList.clear();
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

    @Override
    public String toString() {
        return "Box{" +
                "arrayList=" + arrayList +
                '}';
    }
}
