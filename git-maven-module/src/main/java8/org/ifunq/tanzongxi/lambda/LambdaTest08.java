package org.ifunq.tanzongxi.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest08 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "",
                "sfasf", "ff", "gg", "33", "44", "66", "jj", "sdf", "123", "fdhg", "4234");

        // 自定义写法
        List<String> filtered1 = strings
                // 准备并行Stream
                // .stream()
                .parallelStream()
                .filter(s -> s.length() <= 3 && !s.isEmpty())
                .collect(ArrayList::new,
                        (a, b) -> {
                            System.out.println(a);
                            a.add(b);
                        },
                        (a, b) -> {
                            System.out.println(Thread.currentThread().getName() + "  a:"+a + "   " + "b:"+b);
                            a.addAll(b);
                        });
        System.out.println(filtered1);

        // 方法引用写法
        List<String> filtered2 = strings.parallelStream().filter(s -> s.length() <= 3 && !s.isEmpty()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(filtered2);

        // Java8提供Collectors写法
        List<String> filtered3 = strings.parallelStream().filter(s -> s.length() <= 3 && !s.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered3);


        Map<String, String> filtered4 = strings.parallelStream().filter(s -> s.length() <= 3 && !s.isEmpty()).collect(Collectors.toMap(Object::toString,Object::toString));
        System.out.println(filtered4);
    }
}
