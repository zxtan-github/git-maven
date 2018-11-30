package org.ifunq.tanzongxi.lambda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LambdaTest12 {

    public static void main(String[] args) {

        // 第一种写法:lambda表达式写法
        MathOperation mathOperation1 = (a, b) -> a + b;

        // 第二种写法:匿名类写法
        MathOperation mathOperation2 = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };

        // 第三种写法:方法引用写法
        MathOperation mathOperation3 = MyMathOperation::sum;

        // 第三种写法，方法引用写法实际会转化成以下写法
        MathOperation mathOperation4 = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return MyMathOperation.sum(a, b);
            }
        };

        System.out.println(mathOperation1.operation(1, 2));
        System.out.println(mathOperation2.operation(2, 3));
        System.out.println(mathOperation3.operation(5, 6));
        System.out.println(mathOperation4.operation(6, 7));

        // -------------------------------------------------------------------------
        // 解释下为什么当引用非静态方法引用是，使用Class::method是，第一个参数必须要传对象类型MyMathOperation
        BiConsumer<MyMathOperation, String> sayMsgStr1 = MyMathOperation::sayMsgStr;
        Consumer<MyMathOperation> sayVoidStr1 = MyMathOperation::sayVoidStr;

        // 以上两种写法会转换为下面两种格式，所以，第一个参数必须是对象类型
        BiConsumer<MyMathOperation, String> sayMsgStr2 = new BiConsumer<MyMathOperation, String>() {
            @Override
            public void accept(MyMathOperation myMathOperation, String s) {
                myMathOperation.sayMsgStr(s);
            }
        };
        Consumer<MyMathOperation> sayVoidStr2 = new Consumer<MyMathOperation>() {
            @Override
            public void accept(MyMathOperation myMathOperation) {
                myMathOperation.sayVoidStr();
            }
        };

        // 以上两种写法会转换为lambda表达式的形式
        BiConsumer<MyMathOperation, String> sayMsgStr3 = (MyMathOperation o, String s) -> o.sayMsgStr(s);
        Consumer<MyMathOperation> sayVoidStr3 = (MyMathOperation o) -> o.sayVoidStr();

        // -------------------------------------------------------------------------
        // 解释下对象的方法引用：instance::method，则可以第一个参数可以不是对象类型MyMathOperation
        MyMathOperation myMathOperation = new MyMathOperation();
        Consumer<String> sayMsgStr4 = myMathOperation::sayMsgStr;
        Consumer<String> sayMsgStr5 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                myMathOperation.sayMsgStr(s);
            }
        };
        Consumer<String> sayMsgStr6 = (String s) -> myMathOperation.sayMsgStr(s);
        // instance::method一般都是不建议用的，因为要求lambda里面的对象必须是final修饰
    }
}

class MyMathOperation {
    public static int sum(int a, int b) {
        return a + b;
    }

    public void sayVoidStr() {
        System.out.println("sayStr");
        ;
    }

    public void sayMsgStr(String msg) {
        System.out.println("sayMsgStr the " + msg);
    }

}
