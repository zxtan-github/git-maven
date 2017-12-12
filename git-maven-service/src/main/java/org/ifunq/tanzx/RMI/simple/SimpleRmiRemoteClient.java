package org.ifunq.tanzx.RMI.simple;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI远程注册表客户
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 22:08
 **/
public class SimpleRmiRemoteClient {

    public static void main(String[] args) throws Exception {
        // 获得注册中心，这里是获得远程的注册中心
        Registry registry = LocateRegistry.getRegistry("localhost", 12312);
        // 获取hello1服务，查找注册中心方式
        RmiHello hello1 = (RmiHello) registry.lookup("hello1");
        hello1.printMsg();
        String result = hello1.sayHello("tanzongxi");
        System.out.println(result);
        // 获取hello2服务，Naming方式
        RmiHello hello2 = (RmiHello) Naming.lookup("rmi://localhost:12312/hello2");
        hello2.printMsg();
        result = hello2.sayHello("tanchengyu");
        System.out.println(result);
    }
}