package org.ifunq.tanzongxi.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest01 {

    public static void main(String[] args) {
        // 入参带类型，方法体不带括号
        MathOperation addition = (int a, int b) -> a + b;

        System.out.println(addition.operation(1, 2));
        // 入参不带类型，方法体带小括号
        MathOperation subtraction = (a, b) -> (a - b);
        System.out.println(subtraction.operation(5, 2));

        // 入参不带类型，方法体带大括号，有返回值时，需要显示return
        MathOperation multiplication  = (a, b) -> {return a * b;};
        System.out.println(multiplication .operation(5, 2));

        // 只有一个参数时
        GreetingService greetingService1 = (message) -> System.out.println(message);
        greetingService1.sayMessage("Steven");

        // 只有一个参数时，入参可以不带括号
        GreetingService greetingService2 = message -> System.out.println(message);
        greetingService2.sayMessage("Anna");





        Predicate<Integer> predicate = a -> a > 3;
        System.out.println(predicate.test(5));
        System.out.println(predicate.test(2));


        Supplier<String> supplier = MyString::getStatic;

        Consumer consumer = MyString::getStatic2;

        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(MyString::getStatic2);
    }
}

@FunctionalInterface
interface MathOperation {
    int operation(int a, int b);
}

interface GreetingService {
    void sayMessage(String message);
}



class MyString {

    MyString() {

    }
    MyString(String aa) {

    }

    static String getStatic() {
        return "bac";
    }
    static String getStatic2(Object a) {
        System.out.println("bac");
        return "avc";
    }
}
