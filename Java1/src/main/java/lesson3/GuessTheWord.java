package lesson3;
//Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
// "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
// При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
// сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано,
// компьютер показывает буквы которые стоят на своих местах.
// apple – загаданное
// apricot - ответ игрока
// ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
// Для сравнения двух слов посимвольно, можно пользоваться:
// String str = "apple";
// str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
// Играем до тех пор, пока игрок не отгадает слово
// Используем только маленькие буквы

import java.util.Scanner;

public class GuessTheWord {
    public static void main(String[] args) {

        playGame();
    }

    public static void playGame() {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        //Загадываем слово из массива
        String guessWord = words[(int) (Math.random() * words.length)];
        System.out.println(guessWord); //Для теста

        while (true) {
            //Вводим свое слово и коннвертим его в нижний кейс и обрезаем пробелы вначале и конце введенной строки
            System.out.print("Угадайте слово: "); //Для тестирование
            Scanner sc = new Scanner(System.in);
            String userWord = sc.nextLine().toLowerCase().trim();

            //Если слово угадали то выходим из цикла (игры)
            if (checkWord(guessWord, userWord)) {
                break;
            }
        }
    }

    public static boolean checkWord(String word, String userWord) {
        //Количество символов для скрытия
        int cntHide = 15;
        boolean isWin = false;

        if (userWord.equals(word)) {
            System.out.println("Вы угадали!");
            isWin = true;
        } else {
            //Если слово не угадали то ищем совпадения символов
            System.out.print("Вы не угадали, поиск совпадений: ");
            for (int i = 0; i < word.length() || i < cntHide; i++) {
                //Ловим эксепшен на выход за диапазон длины слова и печатаем #
                try {
                    System.out.print((word.charAt(i) == userWord.charAt(i)) ? word.charAt(i) : "#");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print("#");
                }
            }
        }
        System.out.println();
        return isWin;
    }

}
