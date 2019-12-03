package org.ifunq.tanzongxi.jdk.thread;

import java.util.concurrent.TimeUnit;

public class WaitInterceptTest {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread sub = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    System.out.println("wait方法中断执行");
                }
            }
        });
        sub.start();
        TimeUnit.SECONDS.sleep(1);
        sub.interrupt();
    }
}
