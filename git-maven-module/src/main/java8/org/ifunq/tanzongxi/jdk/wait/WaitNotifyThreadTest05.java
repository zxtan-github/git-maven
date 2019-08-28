package org.ifunq.tanzongxi.jdk.wait;

import java.util.concurrent.TimeUnit;

public class WaitNotifyThreadTest05 {

    /** 进入等待状态 */
    public synchronized void enterWait() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        wait(1000);
        TimeUnit.SECONDS.sleep(1);
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " execute enterWait... " +  (endTime - startTime) +  "ms");
    }
    /** 退出等待状态 */
    public synchronized void outWait() throws InterruptedException {
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyThreadTest05 test05 = new WaitNotifyThreadTest05();

        for (int i= 1 ; i <= 10; i++) {
            Thread subThread =  new Thread(()->{
                try {
                    test05.enterWait();
                } catch (InterruptedException e) {
                }
            });
            subThread.setName("Sub Thread " + i);
            subThread.start();
            // 休眠1ms保证子线程依次启动
            TimeUnit.MILLISECONDS.sleep(1);
        }
        test05.outWait();
    }

}
