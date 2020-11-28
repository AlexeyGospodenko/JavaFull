package lesson3;
//Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
// При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
// После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

import java.util.Scanner;


public class GuessTheNumber {

    public static void main(String[] args) {

        playGame();

    }

    public static void playGame() {

        //Счетчик количества попыток
        int tryCount = 3, number;

        //Диапазон загадываемого числа
        int compNumber = (int) (Math.random() * 10);

        //Угадываем число tryCount раз
        while (true) {
            for (int i = tryCount - 1; i >= 0; i--) {
                System.out.print("Введите число от 0 до 9: ");
                number = inputNumber();

                //Проверка на предмет угадали ли число
                if (number == compNumber) {
                    System.out.println("Вы угадали!");
                } else {
                    System.out.println("Загаданное число" + ((compNumber > number) ? " больше " : " меньше ") + number);
                    System.out.println((i > 0) ? "Осталось попыток: " + i + "\n" : "Вы проиграли");
                }
            }

            //Повторяем игру пока не вышли из неё введя 0
            do {
                System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
                number = inputNumber();
            } while (number != 1 && number != 0);

            if (number == 0) return;
        }
    }

    //Метод ввода числа с обработкой эксепшенов
    public static int inputNumber() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                return sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Необходимо ввести число!");
            }
        }
    }
}

