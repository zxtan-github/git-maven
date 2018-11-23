package org.ifunq.tanzx.base.concurrent.locks;

public class SynchronizedLockTest {

    public static void main(String[] args) throws InterruptedException {
        final WaitModel waitModel = new WaitModel();

        // 第一种加锁加锁方式
        new Thread(new Runnable() {
            public void run() {
                try {
                    waitModel.sayLock1();
                } catch (InterruptedException e) {
                }
            }
        }).start();
        Thread.sleep(1000);
        waitModel.releaseLock();

        Thread.sleep(1000);
        // 第二种加锁加锁方式
        new Thread(new Runnable() {
            public void run() {
                try {
                    waitModel.sayLock2();
                } catch (InterruptedException e) {
                }
            }
        }).start();
        Thread.sleep(1000);
        synchronized(waitModel) {
            waitModel.notify();
        }
    }
}

class WaitModel {

    // 第一种加锁wait方式
    synchronized void sayLock1() throws InterruptedException {
        System.out.println("get lock ...");
        wait();
        System.out.println("release lock ...");
    }

    // 第二种加锁wait方式
    void sayLock2() throws InterruptedException {
        System.out.println("get lock ...");
        synchronized(this) {
            wait();
        }
        System.out.println("release lock ...");
    }

    // 没有加锁wait方式，会报错
    void sayLock3() throws InterruptedException {
        System.out.println("get lock ...");
        wait();
        System.out.println("release lock ...");
    }

    // 释放内部锁
    void releaseLock() throws InterruptedException {
        synchronized(this) {
            // notify();
            // 执行一个3秒的程序完毕后才释放内部锁
            Thread.sleep(3000);
            System.out.println("3s execute ...");
        }
    }
}
