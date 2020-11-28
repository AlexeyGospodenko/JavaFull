package lesson7;

public class Cat {
    private String name;
    private int appetite;
    //Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        satiety = (appetite == 0);
    }

    public void eat(Plate p) {
        //Если коту удалось покушать (хватило еды), сытость = true.
        satiety = p.decreaseFood(appetite);
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void info() {
        System.out.println("name='" + name + '\'' + ", appetite=" + appetite + ", satiety=" + satiety);
    }

}
