package org.ifunq.tanzongxi.jdk.map;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest03 {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(32);
        MyNode myNode1 = new MyNode();
        concurrentHashMap.put(myNode1, "1");
        MyNode myNode2 = new MyNode();
        concurrentHashMap.put(myNode2, "2");
        MyNode myNode3 = new MyNode();
        concurrentHashMap.put(myNode3, "3");
        MyNode myNode4 = new MyNode();
        concurrentHashMap.put(myNode4, "4");
        MyNode myNode5 = new MyNode();
        concurrentHashMap.put(myNode5, "5");
        MyNode myNode6 = new MyNode();
        concurrentHashMap.put(myNode6, "6");
        Iterator iterator = concurrentHashMap.entrySet().iterator();

        concurrentHashMap.remove(myNode1);
        concurrentHashMap.remove(myNode2);
        concurrentHashMap.remove(myNode4);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    static class MyNode{
        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return "0";
        }
    }
}
