package org.ifunq.tanzx.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ExclusiveLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        // 用CAS操作设置同步状态State，当前线程把state改为1
        // 其他线程便修改不了，可以看成当前线程持有了锁。
        protected boolean tryAcquire(int acquires) {
            if (this.compareAndSetState(0, acquires)) {
                // 将持有线程设置为当前线程
                this.setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        // 释放同步状态
        protected boolean tryRelease(int releases) {// 释放同步状态
            if (Thread.currentThread() != this.getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            if (this.getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            this.setExclusiveOwnerThread(null);
            this.setState(0);
            return true;
        }
        // 当前线程是否持有线程
        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
        // 实例化Condition对象
        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }
    //  队列同步器实例
    private final Sync sync = new Sync();
    // 加锁，线程请求到锁则返回，请求不到锁则阻塞
    public void lock() {
        sync.acquire(1);
    }
    // 非阻塞加锁，线程请求到锁返回true，请求不到锁返回false，不会阻塞。
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    // 解锁，
    public void unlock() {
        sync.release(1);
    }
    // 加锁 响应中断，在获取锁的过程中线程被中断抛出InterruptedException异常，停止锁获取
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    // 非阻塞加锁 时间限制
    public boolean tryLock(long time, TimeUnit unit)  throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
    // 实例化 Condition
    public Condition newCondition() {
        return sync.newCondition();
    }
}
