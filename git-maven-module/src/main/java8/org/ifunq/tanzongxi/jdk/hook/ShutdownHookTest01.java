package org.ifunq.tanzongxi.jdk.hook;

public class ShutdownHookTest01 {

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread begin");

                //等待获取许可
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //输出thread over.true
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        waitThread.start();
        //绑定钩子
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("shutdown success");
        }));
        System.exit(0);
    }
}
