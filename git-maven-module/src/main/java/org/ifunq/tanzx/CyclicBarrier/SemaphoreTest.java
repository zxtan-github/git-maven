package org.ifunq.tanzx.CyclicBarrier;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(new Date() + Thread.currentThread().getName() + "第一次获得锁lock");
                        semaphore.acquire();
                        System.out.println(new Date() + Thread.currentThread().getName() + "第二次获得锁lock");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(new Date() + Thread.currentThread().getName() + "第一次释放锁unlock");
                        semaphore.release();
                        System.out.println(new Date() + Thread.currentThread().getName() + "第二次释放锁unlock");
                        semaphore.release();
                    }

                }
            }.start();
        }
    }
}
