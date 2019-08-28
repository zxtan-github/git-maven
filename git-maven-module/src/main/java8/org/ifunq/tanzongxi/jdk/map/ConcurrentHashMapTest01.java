package org.ifunq.tanzongxi.jdk.map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest01 {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(2);
        concurrentHashMap.put("a", "b");
        concurrentHashMap.put("b", "b");
        concurrentHashMap.put("c", "b");

        concurrentHashMap.entrySet().iterator();
        System.out.println(concurrentHashMap);

    }
}
