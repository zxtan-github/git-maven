package org.ifunq.tanzongxi.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LambdaTest06 {
    public static void main(String[] args) {
        // 构造器引用：Class< T >::new
        Supplier<Java8Car> supplier = Java8Car::new;
        final Java8Car java8Car = supplier.get();

        // 静态方法引用：Class::static_method
        Consumer<String> collide = Java8Car::collide;
        // BiConsumer<Java8Car,String>  collide1 = Java8Car::collide; // 静态方法不会将自身引用传入

        // 特定类的任意对象的方法引用：Class::method
        Consumer<Java8Car> repair = Java8Car::repair;
        BiConsumer<Java8Car,String>  follow1 = Java8Car::follow;

        // 特定对象的方法引用：instance::method
        Consumer<String> follow = java8Car::follow;

        collide.accept("collide");
        repair.accept(java8Car);
        follow1.accept(java8Car,"follow1");
        follow.accept("follow");

    }
}

class Java8Car {

    public static void collide(String msg) {
        System.out.println("Collided " + msg);
    }

    public void follow(String msg) {
        System.out.println("Following the " + msg);
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }


}
