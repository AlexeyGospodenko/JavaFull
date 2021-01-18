package lesson3;

public class TestThreads {

    public static void main(String[] args) {

        Object monitor = new Object();

        PrintLetterThread a = new PrintLetterThread(0, 'A', monitor);
        PrintLetterThread b = new PrintLetterThread(1, 'B', monitor);
        PrintLetterThread c = new PrintLetterThread(2, 'C', monitor);
        a.start();
        b.start();
        c.start();

    }

}
