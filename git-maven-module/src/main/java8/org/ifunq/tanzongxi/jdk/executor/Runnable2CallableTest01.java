package org.ifunq.tanzongxi.jdk.executor;

import java.util.Date;
import java.util.concurrent.*;

public class Runnable2CallableTest01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                3000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue());
        executor.allowCoreThreadTimeOut(true);
        Runnable task = ()->{
            System.out.println("伪Callable");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
        };
        Future result = executor.submit(task);
        System.out.println(new Date());
        System.out.println(result.get());
        System.out.println(new Date());
    }
}
