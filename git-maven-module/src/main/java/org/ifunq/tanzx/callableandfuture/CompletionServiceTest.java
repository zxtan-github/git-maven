package org.ifunq.tanzx.callableandfuture;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < 5; i++) {
            completionService.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    Integer resut = new Random().nextInt(5) * 1000;
                    System.out.println(Thread.currentThread().getName() + " sleep " + resut);
                    Thread.sleep(resut);
                    return resut;
                }
            });
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " take " + completionService.take().get());
        }
        executorService.shutdown();
    }
}
