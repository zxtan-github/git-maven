package org.ifunq.tanzongxi.jdk.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0;i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + "获取信号量");
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        semaphore.release(2);

    }
}
