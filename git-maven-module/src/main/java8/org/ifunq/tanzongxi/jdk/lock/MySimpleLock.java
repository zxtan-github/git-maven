package org.ifunq.tanzongxi.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

public class MySimpleLock {
    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer { // 是否处于占用状态

        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, acquires)) {
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

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
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
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));

    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);

    }


    public static void main(String[] args) throws InterruptedException {
        MySimpleLock mySimpleLock = new MySimpleLock();
        mySimpleLock.lock();
        Thread thread = new Thread(()->{
            System.out.println("---start---");
            try {
                // 不能响应中断，一致阻塞
                //mySimpleLock.lock();
                // 可以响应中断
                //mySimpleLock.lockInterruptibly();
                // 可以响应中断，也可以超时退出
                mySimpleLock.tryLock(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                System.out.println("---InterruptedException---");
                throw new NullPointerException();
            } finally {
                System.out.println("---finally---");
            }
            System.out.println("---end---");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }

}
