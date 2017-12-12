package org.ifunq.tanzx.RMI.simple;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Hello实现
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 21:57
 **/
public class RmiHelloImpl implements RmiHello, Serializable {

    private static final long serialVersionUID = -2951967812172546149L;

    public void printMsg() throws RemoteException {
        System.out.println("This is an example RMI program");
    }

    public String sayHello(String name) throws RemoteException {
        return name + " say hello!";
    }
}