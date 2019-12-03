package org.ifunq.tanzongxi.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class RunInfo implements RunInfoMBean {
    @Override
    public void printInfo() {
        System.out.println("program is running...");
    }

    @Override
    public String printMsg(String msg) {
        System.out.println("msg is " + msg);
        return "success";
    }

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=runInfo");
        mBeanServer.registerMBean(new RunInfo(), helloName);
        TimeUnit.HOURS.sleep(1);
    }
}
