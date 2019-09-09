package org.ifunq.tanzongxi.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyExclusiveFifoLock {
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer { // 是否处于占用状态

        // 当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁，将状态设置为0
        protected boolean tryRelease(int releases) {
            if (getState() == 0) throw new
                    IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }
    public void unlock() {
        sync.release(1);
    }

    public static void main(String[] args) throws InterruptedException {
        MyExclusiveFifoLock myExclusiveFifoLock = new MyExclusiveFifoLock();
        myExclusiveFifoLock.lock();
        TimeUnit.MILLISECONDS.sleep(1);
        new Thread(() -> {
            myExclusiveFifoLock.lock();
        }).start();
        new Thread(() -> {
            myExclusiveFifoLock.lock();
        }).start();
        new Thread(() -> {
            myExclusiveFifoLock.lock();
        }).start();

        TimeUnit.MILLISECONDS.sleep(1);
        myExclusiveFifoLock.unlock();

    }

}
