package org.ifunq.tanzongxi.j8.lambda;

public class LambdaTest02 {

    final static String msg1 = "msg1 ";
    static String msg4 = "msg4 ";

    public static void main(String[] args) {
        // lambda闭包引用final全局变量、显式的final局部变量（明确定义final变量）
        final String msg2 = "msg2 ";
        GreetingService greetingService3 = message ->
        {
            String finalMessage = msg1 + msg2 + message;
            System.out.println(finalMessage);
        };
        greetingService3.sayMessage("Anna love Steven");

        String msg3 = "msg3 ";

        // lambda闭包引用全局变量、隐式的final局部变量（方法体内变量值未被更改）
        GreetingService greetingService4 = message ->
        {
            String finalMessage = msg3 + msg4 + message;
            System.out.println(finalMessage);
        };
        greetingService4.sayMessage("Steven love Anna");
        // 如果msg3进行变更，就不是隐式的final局部变量，编译器会发生编译错误
        // msg3 = "";
    }
}
