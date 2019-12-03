package org.ifunq.tanzongxi.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class RunInfoHttpServerTest {
    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName1 = new ObjectName("RunInfoMBeanList:name=runInfo1");
        mBeanServer.registerMBean(new RunInfo(), objectName1);
        ObjectName objectName2 = new ObjectName("RunInfoMBeanList:name=runInfo2");
        mBeanServer.registerMBean(new RunInfo(), objectName2);

        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
//        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
////        server.registerMBean(adapter, adapterName);
////        adapter.start();
    }
}
