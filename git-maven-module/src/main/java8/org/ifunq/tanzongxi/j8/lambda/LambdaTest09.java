package org.ifunq.tanzongxi.j8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest09 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "",
                "sfasf", "ff", "gg", "33", "44", "66", "jj", "sdf", "123", "fdhg", "4234");

        // 自定义写法
        List<String> filtered1 = strings
                .stream()
                .filter(s -> s.length() <= 3 && !s.isEmpty())
                .collect(ArrayList::new,
                        (a, b) -> {
                            System.out.println(Thread.currentThread().getName() + " Map:" + a.hashCode());
                            System.out.println(a);
                            a.add(b);
                        },
                        (a, b) -> {
                            System.out.println(Thread.currentThread().getName() + " reduce1:" + a.hashCode() + " reduce2:" + b.hashCode());
                            System.out.println(Thread.currentThread().getName() + "  a:"+a + "   " + "b:"+b);
                            a.addAll(b);
                        });
        System.out.println(filtered1);

        // 方法引用写法
        List<String> filtered2 = strings.stream().filter(s -> s.length() <= 3 && !s.isEmpty()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(filtered2);

        // Java8提供Collectors写法
        List<String> filtered3 = strings.stream().filter(s -> s.length() <= 3 && !s.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered3);
    }
}
