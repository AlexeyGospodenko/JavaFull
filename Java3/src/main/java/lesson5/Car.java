package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static boolean haveWinner = false;

    private final CyclicBarrier cbPrepare;
    private CountDownLatch cdlFinish;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch finishedCounter) {
        this.race = race;
        this.speed = speed;
        this.cbPrepare = cb;
        this.cdlFinish = finishedCounter;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cbPrepare.await(); //Все участники должны стартовать одновременно, несмотря на разное время подготовки.
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        //Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты)
        if (!haveWinner) {
            haveWinner = true;
            System.out.println("!!! ПОБЕДИТЕЛЬ " + this.name);
        }
        cdlFinish.countDown();
    }
}