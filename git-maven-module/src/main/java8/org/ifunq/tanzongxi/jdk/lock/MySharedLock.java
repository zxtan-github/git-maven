package org.ifunq.tanzongxi.jdk.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MySharedLock {
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer { // 是否处于占用状态

        @Override
        public int tryAcquireShared(int acquires) {
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            return true;
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquireShared(1);
    }
    public void unlock() {
        sync.releaseShared(1);
    }

    public static void main(String[] args) throws InterruptedException {
        MySharedLock sharedLock = new MySharedLock();
        new Thread(()->{sharedLock.lock();}).start();
        new Thread(()->{sharedLock.lock();}).start();
        new Thread(()->{sharedLock.lock();}).start();
    }

}
