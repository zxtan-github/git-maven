package org.ifunq.tanzongxi.jmx;


import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.GcInfo;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class RunInfoMemoryClientTest {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:10086/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbs = jmxc.getMBeanServerConnection();

        //获取远程MemoryMXBean
        MemoryMXBean memoryMXBean=ManagementFactory.newPlatformMXBeanProxy
                (mbs,ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);

        System.out.println("最大内存KB："  + memoryMXBean.getHeapMemoryUsage().getMax() / 1024);
        System.out.println("已用内存KB："  + memoryMXBean.getHeapMemoryUsage().getUsed() / 1024);


        //获取远程GarbageCollectorMXBean
        GarbageCollectorMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy
                (mbs,"java.lang:type=GarbageCollector,name=PS MarkSweep", GarbageCollectorMXBean.class);
        System.out.println("总共回收次数："  + mxBean.getCollectionCount());
        System.out.println("平均回收时间："  + mxBean.getCollectionTime());
        GcInfo lastGcInfo = mxBean.getLastGcInfo();
        System.out.println("最后一次回收执行前："  + lastGcInfo.getMemoryUsageBeforeGc());
        System.out.println("最后一次回收执行后："  + lastGcInfo.getMemoryUsageAfterGc());


        //获取远程ThreadMXBean
        ThreadMXBean mxBean1 = ManagementFactory.newPlatformMXBeanProxy
                (mbs,"java.lang:type=Threading", ThreadMXBean.class);

        System.out.println("活动线程数："+mxBean1.getThreadCount());
        System.out.println("守护线程数："+mxBean1.getDaemonThreadCount());
        System.out.println("峰值线程数："+mxBean1.getPeakThreadCount());

        // 输出所有线程栈信息
        ThreadInfo[] threadInfos = mxBean1.dumpAllThreads(false,false);
        for (ThreadInfo threadInfo : threadInfos)
            System.out.println(threadInfo);


    }
}
