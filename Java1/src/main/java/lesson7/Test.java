//1. Расширить задачу про котов и тарелки с едой.
//2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
//3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true.
//4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
//5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль.
//6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.

package lesson7;

public class Test {

    public static void main(String[] args) {

        //Создать массив котов и тарелку с едой
        final int catsCount = 10;
        Cat[] cats = new Cat[catsCount];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Barsik" + i, 15 + ((int) (Math.random() * 5) + 1));
            cats[i].info();
        }

        Plate plate = new Plate(100);
        plate.info();

        //попросить всех котов покушать из этой тарелки
        System.out.println("\nКормим котов");
        for (Cat cat : cats) {
            System.out.println();
            cat.info(); //и потом вывести информацию о сытости котов в консоль.
            while (!cat.isSatiety()) {
                cat.eat(plate);

                //Если коту не хватило еды, то добавляем корма
                if (!cat.isSatiety()) {
                    System.out.println("Не хватает еды в тарелке, добавляем 10 еды");
                    plate.addFood(10);
                }

                plate.info();
            }
        }

    }
}