package org.ifunq.tanzongxi.jdk.list;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList al = new CopyOnWriteArrayList();
        Collections.addAll(al,"a", "b", "c", "d", "e", "f", "g");
        Iterator iterator = al.iterator();
        // 对原始list进行add增元素操作
        al.add("i");
        // 抛出ConcurrentModificationException异常
        while (iterator.hasNext())
            System.out.print(iterator.next() + ",");
        iterator = al.iterator();
        System.out.println();
        while (iterator.hasNext())
            System.out.print(iterator.next() + ",");
    }
}
