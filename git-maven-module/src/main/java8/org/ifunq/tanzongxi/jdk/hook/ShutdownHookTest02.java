package org.ifunq.tanzongxi.jdk.hook;

public class ShutdownHookTest02 {

    public static void main(String[] args) {
        Thread waitThread = new Thread(() -> {
            System.out.println("sub start");
            for (;;) {
                System.out.println("sub running.");
                System.out.println("sub running..");
                System.out.println("sub running...");
            }
        });
        waitThread.start();
        //绑定钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutdown success");
        }));
        System.exit(0);
        
    }
}
