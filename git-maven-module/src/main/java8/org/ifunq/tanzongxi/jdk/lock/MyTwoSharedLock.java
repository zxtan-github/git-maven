package org.ifunq.tanzongxi.jdk.lock;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义容量为2的共享锁
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2019/9/11 10:31
 */
public class MyTwoSharedLock {
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {

        private volatile int count = 2;

        public Sync() {
            setState(count);
        }

        @Override
        public int tryAcquireShared(int acquires) {
            for (; ; ) {
                if (getState() > 0) {
                    int currentCount = getState();
                    int expCount = currentCount - 1;
                    if (compareAndSetState(currentCount, expCount))
                        return 1;

                } else {
                    return -1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            for (; ; ) {
                int currentCount = getState();
                int expCount = currentCount + 1;
                if (compareAndSetState(currentCount, expCount))
                    return true;
            }
        }
    }

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquireShared(1);
    }

    public void unlock() {
        sync.releaseShared(1);
    }

    public static void main(String[] args) {
        MyTwoSharedLock sharedLock = new MyTwoSharedLock();
        // 产生10个线程获取容量为2的共享锁
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                sharedLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取锁：" + new Date());
                try {
                    TimeUnit.SECONDS.sleep(2); //处理时间2s后释放锁
                } catch (InterruptedException e) {
                }
                sharedLock.unlock();
            }).start();
        }
    }
}
