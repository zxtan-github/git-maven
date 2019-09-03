package org.ifunq.tanzongxi.jdk.lock;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();

        BigDecimal total = new BigDecimal(0);

        total=total.add(new BigDecimal(2.34));
        System.out.println(total.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        BigDecimal sub = new BigDecimal(0.02);
        System.out.println(total.subtract(sub).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
}
