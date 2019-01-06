package org.ifunq.tanzongxi.j8.lambda;

public class LambdaTest04 {

    public static void main(String[] args) {
        FunctionalVehicle vehicle = () -> System.out.println("FunctionalVehicle01");
        vehicle.functionalSay();
        functionalSay(() -> System.out.println("FunctionalVehicle02"));

    }
    public static void functionalSay(FunctionalVehicle vehicle) {
        vehicle.functionalSay();
    }
}

@FunctionalInterface
interface FunctionalVehicle {
    // Java可以给接口编写default方法
    default void sayYourSelf() {
        System.out.println("I'm a FunctionalVehicle!");
    }

    // Java可以给接口编写static方法
    static void sayAny(String name) {
        System.out.println("I'm sayAny! " + name);
    }

    String name = "Vehicle";

    void functionalSay();
}