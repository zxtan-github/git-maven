package org.ifunq.tanzx.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tanzx on 2017/5/5.
 */
public class InterruptTest {

    private  static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        Thread threadFirst = new Thread(getRunnable(), "线程A") ;
        Thread threadSecond = new Thread(getRunnable(), "线程B") ;
        threadFirst.start();
        Thread.sleep(100);
        threadSecond.start();
        Thread.sleep(600);
        threadSecond.interrupt();
        Thread.sleep(600);
        threadSecond.interrupt();

        Thread.interrupted();
        Thread.currentThread().interrupt();

    }


    private static Runnable getRunnable () {
        return new Runnable() {
            public void run() {
                while (true) {
                    try {
                        if (lock.tryLock(500, TimeUnit.MILLISECONDS )) {
                            // if (lock.tryLock()) {
                            // lock.lock();
                            // lock.lockInterruptibly();
                            try {
                                System.out.println(Thread.currentThread().getName()+"获得锁！");
                                Thread.sleep(1000);
                                System.out.println(Thread.currentThread().getName()+"执行完毕！");
                            } finally {
                                System.out.println(Thread.currentThread().getName()+"释放锁！");
                                lock.unlock();
                            }
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName()+"没有获得锁！");
                        }
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName()+"响应中断！");
                    }
                }
            }
        };
    }

}
