package lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore smTunnel;

    public Tunnel(Semaphore sm) {
        this.smTunnel = sm;
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smTunnel.acquire(); //В тоннель не может одновременно заехать больше половины участников (условность).
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smTunnel.release(); //В тоннель не может одновременно заехать больше половины участников (условность).
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
