package org.ifunq.tanzongxi.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RunInfoClientTest {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:10086/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        // 输出所有的MBean名称
        String[] domains = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("doumain[" + i + "]=" + domains[i]);
        }

        // 获取指定的MBean，并执行对应方法
        ObjectName mbeanName = new ObjectName("RunInfoMBeanList:name=runInfo1");
        RunInfoMBean proxy = MBeanServerInvocationHandler.
                newProxyInstance(mbsc, mbeanName, RunInfoMBean.class, false);
        proxy.printInfo();
        System.out.println(proxy.printMsg("tanzongxi"));
    }
}
