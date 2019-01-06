package org.ifunq.tanzongxi.j8.lambda;

import java.util.function.Consumer;

public class LambdaTest05 {

    public static void main(String[] args) {
        // lambda定义有无回值，编译通过
        Consumer consumer1 = (msg) -> System.out.println(msg);
        // lambda定义有返回值，编译错误
        // Consumer consumer2 = (msg) -> {System.out.println(msg); return msg;};
        // 引用带无返回值的方法引用
        Consumer<String> consumer3 = LambdaTest05::sayNoResult;
        // 引用带有返回值的方法引用，但是返回值时获取不到的，因为Consumer.accept方法定义无返回值
        Consumer<String> consumer4 = LambdaTest05::sayWithResult;
        consumer1.accept("Steven");
        consumer3.accept("Anna");
        consumer4.accept("Jack");
    }

    public static void sayNoResult(String msg) {
        System.out.println(msg);
    }
    public static String sayWithResult(String msg) {
        System.out.println(msg);
        return msg;
    }
}
