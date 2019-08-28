package org.ifunq.tanzongxi.jdk.map;

import java.util.Hashtable;
import java.util.Iterator;

public class HashTableTest01 {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put(null,"");
        hashtable.put("a", "a");
        Iterator iterator = hashtable.entrySet().iterator();
        hashtable.put("b", "b");
        iterator.next();
    }

    public class Node<K,V> {

    }
}
