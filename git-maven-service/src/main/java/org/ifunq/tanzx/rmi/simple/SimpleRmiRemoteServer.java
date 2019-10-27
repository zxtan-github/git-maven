package org.ifunq.tanzx.rmi.simple;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI远程注册表服务
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 22:00
 **/
public class SimpleRmiRemoteServer {

    public static void main(String[] args) throws Exception {
        // 原始服务实例
        RmiHello rmiHello = new RmiHelloImpl();
        // 将原始服务实例包装成可注册的Remote服务，相当于动态代理，代理类里面有注册需要的属性、方法等
        // 封装原始服务，这样可以用于远程注册中心的服务注册，不然注册中心的classpath下必须要有实现类，就相当于在注册中心的主机上运行了实现类
        // 封装原始服务后，其实就相当于注册中心只持有了远程服务的引用，不然就是直接持有服务
        // 封装原始服务后，注册中心只有有接口类就可以了，不然实现类也必须存在与注册中心的classpath下
        Remote skeleton = UnicastRemoteObject.exportObject(rmiHello, 0);
        // 查找注册中心方式注册
        // 获得注册中心，这里是获得远程的注册中心
        Registry registry = LocateRegistry.getRegistry("localhost", 12312);
        registry.bind("hello1", skeleton);
        // Naming方式注册
        Naming.bind("rmi://localhost:12312/hello2", skeleton);
        System.err.println("Server ready");
    }
}