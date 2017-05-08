package org.ifunq.tanzx.callableandfuture;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by tanzx on 2017/5/8.
 */
public class SimpleCallableFuture {

    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("callable运行了...");
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        System.out.println(future.get());


        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        FutureTask<Integer> future2 = new FutureTask<Integer>(callable);
        executorService2.submit(future2);
        System.out.println(future2.get());


        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        Future<Integer> future3 = executorService3.submit(callable);
        System.out.println(future3.get());

    }
}
