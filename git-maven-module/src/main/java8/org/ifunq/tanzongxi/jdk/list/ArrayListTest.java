package org.ifunq.tanzongxi.jdk.list;

import java.util.*;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        Collections.addAll(al,"a", "b", "c", "d", "e", "f", "g");
        List alSub = al.subList(1,3);
        alSub.add("h");
        // 输出[a, b, c, h, d, e, f, g]
        System.out.println(al);
        Iterator iterator = al.iterator();
        // 对原始list进行add增元素操作
        al.add("i");
        // 抛出ConcurrentModificationException异常

        al.clear();
        iterator.next();
    }
}
