package org.ifunq.tanzx.threadlocal;

import java.util.HashMap;

/**
 * Created by tanzx on 2017/4/16.
 */
public class HashMapTest {
    private volatile Integer num = 0;
    private HashMap<String, Integer> numMap = new HashMap<String, Integer>();

    private ThreadLocal<HashMap<String, Integer>> seqHashMap = new ThreadLocal<HashMap<String, Integer>>() {
        @Override
        protected HashMap<String, Integer> initialValue() {
            return numMap;
        }
    };

    public synchronized Integer addHashMap() {
        numMap.put("num", ++num);
        return seqHashMap.get().get("num");
    }

    public static void main(String[] args) {
        HashMapTest numTest = new HashMapTest();
        HashMapThread thread01 = new HashMapThread(numTest);
        HashMapThread thread02 = new HashMapThread(numTest);
        HashMapThread thread03 = new HashMapThread(numTest);
        thread01.start();
        thread02.start();
        thread03.start();
    }

}

class HashMapThread extends Thread {
    HashMapTest numTest;
    public HashMapThread(HashMapTest numTest) {
        this.numTest = numTest;
    }

    @Override
    public void run() {
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   HashMap:" + numTest.addHashMap());
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   HashMap:" + numTest.addHashMap());
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   HashMap:" + numTest.addHashMap());

    }
}
