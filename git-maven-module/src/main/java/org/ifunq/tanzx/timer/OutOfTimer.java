package org.ifunq.tanzx.timer;

import java.util.Timer;
import java.util.TimerTask;

public class OutOfTimer {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("111");
                throw new RuntimeException();
            }
        }, 1);
        Thread.sleep(1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("222");
                throw new RuntimeException();
            }
        }, 1);
    }
}
