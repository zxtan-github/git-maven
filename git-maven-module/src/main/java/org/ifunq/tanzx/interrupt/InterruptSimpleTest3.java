package org.ifunq.tanzx.interrupt;

/**
 * Created by tanzx on 2017/5/5.
 */
public class InterruptSimpleTest3 {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(getRunnable(), "B");
        thread.start();
        thread.interrupt();
    }

    private static Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                int i =0;
                while (i < 100000) {
                    System.out.println(i);i++; // interrupt时线程还没有进入阻塞
                }
                try {
                    Thread.sleep(3000000); // 进入阻塞后线程立刻被中断，因为中断标志为true
                    System.out.println(Thread.currentThread().getName() + "线程阻塞完毕");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "线程被中断了");
                    Thread.currentThread().interrupt();//重新设置中断标示
                }
            }
        };
    }
}
