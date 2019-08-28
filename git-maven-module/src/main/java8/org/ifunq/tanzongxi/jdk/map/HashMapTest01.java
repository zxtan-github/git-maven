package org.ifunq.tanzongxi.jdk.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest01 {


    public static void main(String[] args) {
        HashMap map = new HashMap(17);
        map.put(null, null);
        map.put("s", "s");
        map.put("k", "k");
        Set<Map.Entry> kvSet = map.entrySet();
        map.keySet();
        map.values();
        kvSet.iterator();
        for (Map.Entry kvE : kvSet) {
            map.put("a", "a");
            System.out.println(kvE);
        }

        HashTableTest01  hashTableTest01 = new  HashTableTest01();
        HashTableTest01.Node node =  hashTableTest01.new Node();
    }
}
