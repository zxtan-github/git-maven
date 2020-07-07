package org.ifunq.tanzongxi.unsafe;

        import sun.misc.Unsafe;

        import java.lang.reflect.Field;

public class UnsafeTest01 {
    public static void main(String[] args) throws Exception {
        // Unsafe unsafe = Unsafe.getUnsafe(); // 非JDK源码下的路径，不能使用，会报安全异常
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        Integer var = new Integer(1);
        System.out.println(var);
        // String中属性value的偏移量
        long valueOffset = unsafe.objectFieldOffset(String.class.getDeclaredField("value"));
        boolean result = unsafe.compareAndSwapInt(var, valueOffset, 1,  2);
        System.out.println(result);
        System.out.println(var);
        // 直接设定值
        unsafe.getAndSetInt(var, valueOffset,  5);
        System.out.println(var);
    }
}
