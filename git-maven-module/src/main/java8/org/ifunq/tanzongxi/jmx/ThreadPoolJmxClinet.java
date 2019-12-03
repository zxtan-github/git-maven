package org.ifunq.tanzongxi.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class ThreadPoolJmxClinet {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:10086/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        // 获取指定的MBean，并执行对应方法
        ObjectName mbeanName = new ObjectName("ThreadPoolJmx:name=threadPoolJmx");
        ThreadPoolJmxMBean proxy = MBeanServerInvocationHandler.
                newProxyInstance(mbsc, mbeanName, ThreadPoolJmxMBean.class, false);
        System.out.println(proxy.getCorePoolSize());
        System.out.println(proxy.getPoolSize());
        proxy.shutdown();

    }
}
