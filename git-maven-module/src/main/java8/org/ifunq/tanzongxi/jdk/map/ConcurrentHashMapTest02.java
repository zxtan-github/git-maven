package org.ifunq.tanzongxi.jdk.map;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest02 {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(2);
        concurrentHashMap.put("a", "a");
        concurrentHashMap.put("b", "b");
        concurrentHashMap.put("c", "c");

        Iterator iterator =concurrentHashMap.entrySet().iterator();
        concurrentHashMap.put("d", "d");
        while (iterator.hasNext()) {
            concurrentHashMap.put("a", "aa");
            concurrentHashMap.remove("a");
            concurrentHashMap.remove("b");
            System.out.println(iterator.next());
        }
    }
}
