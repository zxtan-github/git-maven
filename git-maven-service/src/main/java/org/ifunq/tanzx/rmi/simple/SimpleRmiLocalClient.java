package org.ifunq.tanzx.rmi.simple;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI本地注册表客户
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 22:08
 **/
public class SimpleRmiLocalClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // 获得注册中心，这里是获得本地的注册中心
        Registry registry = LocateRegistry.getRegistry(12312);
        // 获取helloSkeleton服务，helloSkeleton是被包装过的
        RmiHello stub = (RmiHello) registry.lookup("helloSkeleton");
        stub.printMsg();
        String result = stub.sayHello("tanzongxi");
        System.out.println(result);
        // 获取hello服务，hello是原始服务实例
        RmiHello rmiHello = (RmiHello) registry.lookup("hello");
        rmiHello.printMsg();
        result = stub.sayHello("tanchengyu");
        System.out.println(result);
    }
}