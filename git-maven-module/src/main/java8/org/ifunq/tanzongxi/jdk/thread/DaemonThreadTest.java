package org.ifunq.tanzongxi.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程demo
 *
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2019/8/26 15:02
 */
public class DaemonThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
//            try {
//                System.out.println("DaemonThread start");
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println("DaemonThread end");
//            } catch (Exception e) {
//            } finally {
//                System.out.println("DaemonThread execute finally");
//            }
            while (true) {
                System.out.println("DaemonThread end");
            }
        });
        // 设置线程为守护线程
        thread.setDaemon(true);
        // 守护线程启动
        thread.start();

        // 主线程休眠1s运行结束
        TimeUnit.SECONDS.sleep(1);

        // 守护线程只执行了System.out.println("DaemonThread start");其他都没法执行
    }
}

