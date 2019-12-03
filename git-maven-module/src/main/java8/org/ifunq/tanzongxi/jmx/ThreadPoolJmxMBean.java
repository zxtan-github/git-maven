package org.ifunq.tanzongxi.jmx;

public interface ThreadPoolJmxMBean {
    // 获取核心线程
    int getCorePoolSize();
    // 获取总线程池大小
    int getPoolSize();
    // 关闭线程池
    void shutdown();
}
