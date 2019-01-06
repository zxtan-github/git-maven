package org.ifunq.tanzongxi.j8.lambda;

public class MyCar implements Vehicle, Car {
    // 当实现的接口中包含两个同样的方法时（不管有没有默认实现），必须复写定义方法
    // 至于复写方法里面是自己逻辑还是调用接口的默认方法逻辑，看自己编写
    @Override
    public void sayYourSelf() {
        // 自己逻辑和接口定义的默认方法没有关系
        System.out.println("I'm my car!");
        // 调用接口的默认方法
        Vehicle.super.sayYourSelf();
        Car.super.sayYourSelf();
    }

    public static void main(String[] args) {
        MyCar myCar = new MyCar();
        myCar.sayYourSelf();
        Vehicle.sayAny(Vehicle.name);
        Car.sayAny(Car.name);
    }
}

interface Vehicle {
    default void sayYourSelf() {
        System.out.println("I'm a vehicle!");
    }

    // Java可以给接口编写static方法
    static void sayAny(String name) {
        System.out.println("I'm sayAny! " + name);
    }

    String name = "Vehicle";
}

interface Car {
    default void sayYourSelf() {
        System.out.println("I'm a car!");
    }

    static void sayAny(String name) {
        System.out.println("I'm sayAny! " + name);
    }

    String name = "Car";
}
