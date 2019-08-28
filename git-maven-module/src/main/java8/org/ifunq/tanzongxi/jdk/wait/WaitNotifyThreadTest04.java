package org.ifunq.tanzongxi.jdk.wait;

import java.util.concurrent.TimeUnit;

public class WaitNotifyThreadTest04 {

    static Object lock =new Object();


    /** 进入等待状态 */
    public synchronized void enterWait() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        wait();
        System.out.println("enterWait execute");
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " execute enterWait... " +  (endTime - startTime) +  "ms");
    }
    /** 退出等待状态 */
    public synchronized void outWait() throws InterruptedException {
        notify();
        TimeUnit.SECONDS.sleep(2);
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyThreadTest04 test02 = new WaitNotifyThreadTest04();
        new Thread(()->{
            try {
                test02.enterWait();
            } catch (InterruptedException e) {
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        test02.outWait();
    }

}
