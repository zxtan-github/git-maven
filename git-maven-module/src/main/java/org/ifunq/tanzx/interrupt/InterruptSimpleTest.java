package org.ifunq.tanzx.interrupt;

/**
 * Created by tanzx on 2017/5/5.
 */
public class InterruptSimpleTest {

    public static void main(String[] args) throws InterruptedException {
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("线程没有被中断");
        } else {
            System.out.println("线程被中断了");
        }
        Thread.currentThread().interrupt();
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("线程没有被中断");
        } else {
            System.out.println("线程被中断了");
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("线程没有被中断");
        } else {
            System.out.println("线程被中断了");
        }


        Thread.interrupted();
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("线程没有被中断");
        } else {
            System.out.println("线程被中断了");
        }


        Thread thread = new Thread();
        if (thread.isInterrupted()) {
            System.out.println("线程被中断了");
        } else {
            System.out.println("线程没有被中断");
        }
        thread.interrupt();
        thread.start();
        thread.interrupt();
        Thread.sleep(100);
        if (thread.isInterrupted()) {
            System.out.println("线程被中断了");
        } else {
            System.out.println("线程没有被中断");
        }
    }
}


