package org.ifunq.tanzongxi.jdk.executor;

import java.util.Date;
import java.util.concurrent.*;

public class Runnable2CallableTest02 {

    final static RunnableResult realResult = new RunnableResult();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue());
        Runnable task = ()->{
            System.out.println("真Callable");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            // 如果这句没有执行，一样获取不到结果
            realResult.realResult = "ABC";
        };
        Future result = executor.submit(task, realResult);
        System.out.println(new Date());
        System.out.println(result.get());
        System.out.println(new Date());
    }
}
class RunnableResult {
    public String realResult;
    public String toString() {
        return realResult;
    }
}
