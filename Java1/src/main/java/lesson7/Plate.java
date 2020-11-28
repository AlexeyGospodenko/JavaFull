package lesson7;


public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        //Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20)
        if (food > n) {
            food -= n;
            System.out.println("Из тарелке съедено " + n + " еды");
            return true;
        } else return false; //Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт
    }

    //Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
    public void addFood(int food) {
        this.food += food;
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}

