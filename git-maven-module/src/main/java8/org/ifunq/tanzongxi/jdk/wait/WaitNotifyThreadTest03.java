package org.ifunq.tanzongxi.jdk.wait;

import java.util.concurrent.TimeUnit;

public class WaitNotifyThreadTest03 {

    final Object lock = new Object();

    /**
     * 进入等待状态
     */
    public void enterWait() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        synchronized (lock) {
            lock.wait();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " execute enterWait... " + (endTime - startTime) + "ms");
    }

    /**
     * 退出等待状态
     */
    public void outWait() throws InterruptedException {
        synchronized (lock) {
            lock.notify();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    /**
     * 退出等待状态
     */
    public void otherSynchronizeMethod() throws InterruptedException {
        synchronized (lock) {
            // 耗时处理，需要一秒，Thread.sleep保证锁不被释放
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " execute otherSynchronizeMethod... ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyThreadTest03 test03 = new WaitNotifyThreadTest03();
        // 子线程A进入wait状态
        Thread subThreadA = new Thread(() -> {
            try {
                test03.enterWait();
            } catch (InterruptedException e) {
            }
        });
        subThreadA.setName("Sub Thread A");
        subThreadA.start();
        // 休眠1ms保证子线程A先启动
        TimeUnit.MILLISECONDS.sleep(1);
        // 子线程B通知
        Thread subThreadB = new Thread(() -> {
            try {
                test03.outWait();
            } catch (InterruptedException e) {
            }
        });
        subThreadB.setName("Sub Thread B");
        subThreadB.start();
        // 休眠1ms保证子线程B启动
        TimeUnit.MILLISECONDS.sleep(30);

        for (int i = 1; i <= 10; i++) {
            Thread subThread = new Thread(() -> {
                try {
                    test03.otherSynchronizeMethod();
                } catch (InterruptedException e) {
                }
            });
            subThread.setName("Sub Thread " + i);
            subThread.start();
            // 休眠1ms保证子线程依次启动
            TimeUnit.MILLISECONDS.sleep(1);
        }


    }

}


