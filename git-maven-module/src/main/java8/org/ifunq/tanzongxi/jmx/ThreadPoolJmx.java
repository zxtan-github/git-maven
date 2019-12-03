package org.ifunq.tanzongxi.jmx;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolJmx implements ThreadPoolJmxMBean {
    ThreadPoolExecutor threadPoolExecutor;

    ThreadPoolJmx(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public int getCorePoolSize() {
        return threadPoolExecutor.getCorePoolSize();
    }

    public int getPoolSize() {
        return threadPoolExecutor.getPoolSize();
    }

    public void shutdown() {
        threadPoolExecutor.shutdownNow();
    }

}
