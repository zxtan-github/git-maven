package org.ifunq.tanzongxi.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;

public class RunInfoServerTest {
    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName1 = new ObjectName("RunInfoMBeanList:name=runInfo1");
        mBeanServer.registerMBean(new RunInfo(), objectName1);
        ObjectName objectName2 = new ObjectName("RunInfoMBeanList:name=runInfo2");
        mBeanServer.registerMBean(new RunInfo(), objectName2);

        LocateRegistry.createRegistry(10086);
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:10086/jmxrmi");
        JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
        jcs.start();
        TimeUnit.HOURS.sleep(1);
    }
}
