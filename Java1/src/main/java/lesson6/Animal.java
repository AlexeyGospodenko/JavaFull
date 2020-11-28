//1. Создать классы Собака и Кот с наследованием от класса Животное.
//2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая
// или длину препятствия (для бега и плавания), или высоту (для прыжков).
//3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
//4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
//5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.

package lesson6;

public class Animal {

    protected int maxDistance;
    protected float maxHeight;
    protected String name;

    public Animal(int maxDistance, float maxHeight, String name) {

        this.maxDistance = (int) ((maxDistance * 0.8) + (Math.random() * maxDistance * 0.4 + 1));
        this.maxHeight = (float) ((maxHeight * 0.8) + (Math.random() * maxHeight * 0.4));
        this.name = name;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public float getMaxHeight() {
        return maxHeight;
    }

    public void run(int distance) {
        System.out.println(name + ".run -> " + (distance <= maxDistance));
    }

    public void jump(float height) {
        System.out.println(name + ".jump -> " + (height <= maxHeight));
    }

    @Override
    public String toString() {
        return "Animal{" +
                "maxDistance=" + maxDistance +
                ", maxHeight=" + maxHeight +
                ", name='" + name + '\'' +
                '}';
    }
}
