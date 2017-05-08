package org.ifunq.tanzx.CyclicBarrier;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {

        final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println(new Date() + Thread.currentThread().getName() + " 执行了barrierAction");
            }
        });
        for (int i = 1; i < 7; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        int wait = new Random().nextInt(5) * 1000;
                        System.out.println(new Date() + Thread.currentThread().getName() + " await " + wait + "ms");
                        Thread.sleep(wait);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + Thread.currentThread().getName() + " 执行完毕");
                }
            }.start();
        }
    }
}
