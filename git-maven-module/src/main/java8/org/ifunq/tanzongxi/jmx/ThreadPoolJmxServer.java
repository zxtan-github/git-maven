package org.ifunq.tanzongxi.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolJmxServer {
    public static void main(String[] args) throws Exception {
        // 创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        // 执行一个任务
        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
            }
        });

        // 将线程池放入MBean中
        ThreadPoolJmx threadPoolJmx = new ThreadPoolJmx(threadPoolExecutor);

        // 发布MBean
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName objectName = new ObjectName("ThreadPoolJmx:name=threadPoolJmx");
        mBeanServer.registerMBean(threadPoolJmx, objectName);

        LocateRegistry.createRegistry(10086);
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:10086/jmxrmi");
        JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
        jcs.start();

    }
}
