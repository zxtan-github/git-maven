package org.ifunq.tanzongxi.jdk.thread;

public class ThreadInterruptedTest {

    public static void main(String[] args) {
        System.out.println("第一次判断是否中断：" + Thread.currentThread().isInterrupted());
        // 执行中断
        Thread.currentThread().interrupt();
        System.out.println("第二次判断是否中断：" + Thread.currentThread().isInterrupted());
        System.out.println("第三次判断是否中断，并清除中断状态：" + Thread.interrupted());
        System.out.println("第四次判断是否中断：" + Thread.currentThread().isInterrupted());
    }
}