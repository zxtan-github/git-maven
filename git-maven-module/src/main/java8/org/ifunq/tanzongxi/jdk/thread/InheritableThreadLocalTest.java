package org.ifunq.tanzongxi.jdk.thread;

public class InheritableThreadLocalTest {

    static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal<String>();
    static ThreadLocal threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {
        inheritableThreadLocal.set("InheritableThreadLocal");
        threadLocal.set("ThreadLocal");
        // 主线程获取自己的ThreadLocal和InheritableThreadLocal对象
        System.out.println("Main thread get "+inheritableThreadLocal.get());
        System.out.println("Main thread get "+threadLocal.get());
        // 子线程获取主线程的ThreadLocal和InheritableThreadLocal对象
        new Thread(()->{
            System.out.println("Sub thread get "+inheritableThreadLocal.get());
            System.out.println("Sub thread get "+threadLocal.get());
            // 子线程中再创建子线程
            new Thread(()->{
                System.out.println("Sub sub thread get "+inheritableThreadLocal.get());
                System.out.println("Sub sub thread get "+threadLocal.get());
            }).start();
        }).start();

    }
}
