package org.ifunq.tanzongxi.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LambdaTest11 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "",
                "sfasf", "ff", "gg", "33", "44", "66", "jj", "sdf", "123", "fdhg", "4234");
        List<String> filtered = strings.stream().filter(s -> s.length() <= 3 && !s.isEmpty()).collect(new MyListCollector());
        System.out.println(filtered);
    }
}

class MyListCollector implements Collector<Object, List, List> {

    @Override
    public Supplier<List> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List, Object> accumulator() {
        return (List left, Object right) -> left.add(right);
    }

    @Override
    public BinaryOperator<List> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List, List> finisher() {
        return (left) -> left;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}


class MyListCollector2<Object, List> implements Collector {

    @Override
    public Supplier<java.util.List> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<java.util.List, Object> accumulator() {
        return (java.util.List left, Object right) -> left.add(right);
    }

    @Override
    public BinaryOperator<java.util.List> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List, List> finisher() {
        return (left) -> left;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}