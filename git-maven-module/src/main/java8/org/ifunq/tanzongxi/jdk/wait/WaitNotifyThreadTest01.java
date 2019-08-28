package org.ifunq.tanzongxi.jdk.wait;

public class WaitNotifyThreadTest01 {

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
    public  void enterWait() throws InterruptedException {
        wait();
    }
    /** 退出等待状态 */
    public void outWait() throws InterruptedException {
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyThreadTest01 waitThreadTest = new WaitNotifyThreadTest01();
        waitThreadTest.overOverEnterWait();
        // waitThreadTest.outWait();
    }

}
