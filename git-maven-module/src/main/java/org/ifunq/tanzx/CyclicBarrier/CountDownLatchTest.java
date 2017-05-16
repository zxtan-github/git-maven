package org.ifunq.tanzx.CyclicBarrier;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);
        for (int i = 1; i < 4; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                }
            }.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线执行完毕，主线程继续执行");
    }

}
