package org.ifunq.tanzx.anonymousclass;

import java.lang.reflect.Field;

public class OuterTest {

    public static void main (String[] arg) throws IllegalAccessException {
        // 内部类
        Outer outter = new Outer();
        outter.setName("tanzx");
        Outer.Inner inner = outter.new Inner();
        Field[] fields = inner.getClass().getDeclaredFields();
        System.out.println("=========内部类=========");
        System.out.println(inner.getClass());
        for (Field f : fields) {
            System.out.println(f);
        }
        Outer out1 =(Outer) fields[1].get(inner);
        System.out.println(out1);
        System.out.println(outter);
        System.out.println(out1.getName());
        System.out.println(outter.getName());
        // 匿名内部类
        Other other = outter.getAnonymousOther();
        fields = other.getClass().getDeclaredFields();
        System.out.println("=========匿名内部类=========");
        System.out.println(other.getClass());
        for (Field f : fields) {
            System.out.println(f);
        }
        Outer out2 =(Outer) fields[0].get(other);
        System.out.println(out2);
        System.out.println(outter);
        System.out.println(out2.getName());
        System.out.println(outter.getName());
    }
}
