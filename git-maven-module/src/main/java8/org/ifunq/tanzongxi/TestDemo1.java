package org.ifunq.tanzongxi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TestDemo1 {

    static Object o1 = new Object();
    static Object o2 = new Object();
    static String msg = "213";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {
        new Thread(() -> {
            synchronized (o1) {
                System.out.println("======o1======");
                try {
                    Thread.sleep(5000);
                    msg = "123";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        Thread.sleep(100);
        new Thread(() -> {
            synchronized (o2) {
                System.out.println("======o2======");
                msg = "456";
            }

        }).start();
        Thread.sleep(6000);
        System.out.println(msg);

    }

}


