package org.ifunq.tanzx.rmi.simple;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI本地注册表服务
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 22:00
 **/
public class SimpleRmiLocalServer {

    public static void main(String[] args) throws Exception {
        // 原始服务实例
        RmiHello rmiHello = new RmiHelloImpl();
        // 将原始服务实例包装成可注册的Remote服务，相当于动态代理，代理类里面有注册需要的属性、方法等
        // 这一步不是必须的，直接注册原始服务实例也是可行的
        Remote skeleton = UnicastRemoteObject.exportObject(rmiHello, 0);
        // 获得注册中心，这里是获得本地的注册中心
        // Registry registry = LocateRegistry.getRegistry();
        // 代码中启动一个注册中心，并设定端口为12312
        Registry registry = LocateRegistry.createRegistry(12312);
        // 注册中心中注册Remote服务，并设定一个服务名称
        registry.bind("helloSkeleton", skeleton);
        // 也可以直接注册原始服务实例
        registry.bind("hello", rmiHello);
        System.err.println("Server ready");
    }
}