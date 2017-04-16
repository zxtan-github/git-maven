package org.ifunq.tanzx.threadlocal;

/**
 * Created by tanzx on 2017/4/16.
 */
public class NumTest {
    private volatile Integer num = 0;

    private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            System.out.println("execute initialValue...");
            return num;
        }
    };

    public Integer addNum() {
        ++num;
        return seqNum.get();
    }

    public static void main(String[] args) {
        NumTest numTest = new NumTest();
        NumThread thread01 = new NumThread(numTest);
        NumThread thread02 = new NumThread(numTest);
        NumThread thread03 = new NumThread(numTest);
        thread01.start();
        thread02.start();
        thread03.start();
    }

}
class NumThread extends Thread {
    NumTest numTest;
    public NumThread(NumTest numTest) {
        this.numTest = numTest;
    }

    @Override
    public void run() {
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   Num:" + numTest.addNum());
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   Num:" + numTest.addNum());
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   Num:" + numTest.addNum());

    }
}
