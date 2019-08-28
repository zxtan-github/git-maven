package org.ifunq.tanzongxi.jdk.wait;

import java.util.concurrent.TimeUnit;

public class WaitNotifyThreadTest02 {

    static Object lock =new Object();

    /** 进入等待状态的上上层方法 */
    public void overOverEnterWait() throws InterruptedException {
        synchronized(this) {
            overEnterWait();
        }
    }
    /** 进入等待状态的上层方法 */
    public  void overEnterWait() throws InterruptedException {
        synchronized(this) {
            enterWait();
        }
    }

    /** 进入等待状态 */
    public synchronized void enterWait() throws InterruptedException {
        wait();
        System.out.println("enterWait execute");
    }
    /** 退出等待状态 */
    public synchronized void outWait() throws InterruptedException {
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyThreadTest02 test02 = new WaitNotifyThreadTest02();
        new Thread(()->{
            try {
                test02.overOverEnterWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        test02.outWait();
    }

}
