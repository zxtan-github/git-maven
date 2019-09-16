package org.ifunq.tanzongxi.jdk.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {



    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();
        lock.writeLock().unlock();
        lock.readLock().lock();
        lock.readLock().unlock();

    }
}
