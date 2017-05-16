package org.ifunq.tanzx.callableandfuture;

import java.util.concurrent.*;

public class SimpleCancelFutureTest {
    public static void main(String[] args)  {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 1234;
            }
        });
        try {
            Integer result = future.get(3000, TimeUnit.MILLISECONDS);
            System.out.println("result--->" + result);
        } catch (InterruptedException e) {
            future.cancel(true);
        } catch (ExecutionException e) {
            future.cancel(true);
        } catch (TimeoutException e) {
            System.out.println("result--->超时取消");
            future.cancel(true);
        }
        executorService.shutdown();
    }
}
