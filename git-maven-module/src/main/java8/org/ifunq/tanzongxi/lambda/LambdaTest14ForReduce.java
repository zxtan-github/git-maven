package org.ifunq.tanzongxi.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LambdaTest14ForReduce {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "",
                "sfasf", "ff", "gg", "33", "44", "66", "jj", "sdf", "123", "fdhg", "4234");

        // 自定义串行reduce
        List list1 = new ArrayList<>();
        List<String> filtered1 = strings
                .stream()
                .filter(s -> s.length() <= 3 && !s.isEmpty())
                .reduce(list1,
                        (a, b) -> {
                            a.add(b);
                            return a;
                        },
                        (a, b) -> {
                            a.addAll(b);
                            System.out.println("filtered1 combiner...");
                            return a;
                        });
        System.out.println(filtered1);

        //  自定义并行reduce
        List list2 = new CopyOnWriteArrayList();
        List<String> filtered2 = strings
                .parallelStream()
                .filter(s -> s.length() <= 3 && !s.isEmpty())
                .reduce(list2,
                        (a, b) -> {
                            a.add(b);
                            return a;
                        },
                        (a, b) -> {
                            // 不能执行以下两个程序，报错
                            // a.addAll(b);
                            System.out.println(Thread.currentThread().getName() + "  a:"+a + "   " + "b:"+b);
                            System.out.println("filtered2 combiner...");
                            return a;
                        });
        System.out.println(filtered2);

    }
}
