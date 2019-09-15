package org.ifunq.tanzongxi.jdk.executor;

import java.util.concurrent.*;

public class ExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                3000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        executor.allowCoreThreadTimeOut(true);
        executor.execute(() -> {
            System.out.println("=========");
        });
        executor.execute(() -> {
            System.out.println("=========");
        });
        Future<String> future = executor.submit(() -> {
            return "sss";
        });
        System.out.println(future.get());;
    }
}
