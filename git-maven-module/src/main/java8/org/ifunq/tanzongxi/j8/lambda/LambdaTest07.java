package org.ifunq.tanzongxi.j8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest07 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl", "",
                "sfasf", "ff", "gg", "33", "44", "66", "jj", "sdf", "123", "fdhg", "4234");
        List<String> filtered = strings
                // 准备并行Stream
                .parallelStream()
                // .stream()
                // 过滤掉为空数据
                .filter(string -> {System.out.println(string);  return  !string.isEmpty();})
                // 过滤掉长度大于3的数据
                .filter(str -> str.length() <= 3)
                // 取前10条数据
                .limit(10)
                // 组成新的数组
                .collect(Collectors.toList());
        System.out.println(filtered);

//        filtered.stream().max();
    }
}
