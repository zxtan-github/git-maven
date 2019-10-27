package org.ifunq.tanzx.rmi.simple;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Hello接口
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 21:52
 **/
interface RmiHello extends Remote {
    void printMsg() throws RemoteException;
    String sayHello(String name) throws RemoteException;
}