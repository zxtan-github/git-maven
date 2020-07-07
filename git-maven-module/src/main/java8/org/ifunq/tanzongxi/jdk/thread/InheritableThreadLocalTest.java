package org.ifunq.tanzongxi.jdk.thread;

import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalTest {

    static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal<ThreadLocalObject>();
    static ThreadLocal threadLocal = new ThreadLocal<ThreadLocalObject>();

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalObject localObject = new ThreadLocalObject("InheritableThreadLocal");
        inheritableThreadLocal.set(localObject);
        threadLocal.set(new ThreadLocalObject("ThreadLocal"));
        // 主线程获取自己的ThreadLocal和InheritableThreadLocal对象
        System.out.println("Main thread get "+inheritableThreadLocal.get());
        System.out.println("Main thread get "+threadLocal.get());
        // 子线程获取主线程的ThreadLocal和InheritableThreadLocal对象
        new Thread(()->{
            System.out.println("Sub thread get "+inheritableThreadLocal.get());
            System.out.println("Sub thread get "+threadLocal.get());
            // 主线程对原始的InheritableThreadLocal变量进行修改，对子线程有影响，说明子线程是也是引用的同一个变量，不是深拷贝
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println("Sub sub thread get "+inheritableThreadLocal.get());
            System.out.println("Sub sub thread get "+threadLocal.get());
            // 子线程中再创建子线程
            new Thread(()->{
                System.out.println("Sub sub thread get "+inheritableThreadLocal.get());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {}
                System.out.println("Sub sub thread get "+inheritableThreadLocal.get());
            }).start();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        // 主线程对原始的InheritableThreadLocal变量进行修改，对子线程有影响，说明子线程是也是引用的同一个变量，不是深拷贝
        localObject.value = "InheritableThreadLocal2";
        System.out.println("Main thread get "+inheritableThreadLocal.get());

    }
}
class ThreadLocalObject {
    public String value;

    public ThreadLocalObject (String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}