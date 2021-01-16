package lesson3;

import java.util.concurrent.TimeUnit;

public class PrintLetterThread extends Thread {

    private static int currNum = 0;
    private int number;
    private final char letter;
    private final Object monitor;
    private boolean running = true;
    private int runsCount = 0;

    public PrintLetterThread(int number, char letter, Object monitor) {
        this.number = number;
        this.letter = letter;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (running) {
            if (currNum == number) {
                System.out.print(letter);
                running = (++runsCount != 5);
                synchronized (monitor) {
                    currNum = (currNum + 1) % 3;
                    monitor.notifyAll();
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
