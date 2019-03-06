package org.ifunq.tanzongxi.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class LambdaTest13ForReduce {

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(3, 4, 5, 6, 1, 8, 3);
        BinaryOperator<Integer> binaryOperator = (a, b) -> {if (a>=b) return a; else return b;};

        // Optional<T> reduce(BinaryOperator<T> accumulator)
        Optional optional = ints.parallelStream().reduce(binaryOperator);
        System.out.println(optional.get());

        // T reduce(T identity, BinaryOperator<T> accumulator);
        Integer max = ints.parallelStream().reduce(9, binaryOperator);
        System.out.println(max);

        Optional optional1 = Optional.ofNullable(null);
        System.out.println(optional1.orElseGet(String::new));
    }
}
