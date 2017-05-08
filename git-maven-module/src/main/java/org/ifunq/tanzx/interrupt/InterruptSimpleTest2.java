package org.ifunq.tanzx.interrupt;

/**
 * Created by tanzx on 2017/5/5.
 */
public class InterruptSimpleTest2 {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(getRunnable(), "B");
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
        while (true) {
            if (thread.isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "线程被中断，退出循环！");
                break;
            } else {
                System.out.println(Thread.currentThread().getName() + "线程没有检测到被中断，继续循环！");
                Thread.sleep(1000);
            }
        }
    }

    private static Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "线程被中断了");
                    Thread.currentThread().interrupt();//重新设置中断标示
                }
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "线程又被中断了");
                }
                // do something 后续处理
                for(;;);

            }
        };
    }
}
