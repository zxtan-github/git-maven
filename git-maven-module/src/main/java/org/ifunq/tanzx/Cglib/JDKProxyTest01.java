package org.ifunq.tanzx.Cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest01 {

    public static void main(String[] args) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new UserServiceImpl());
        UserService userService = (UserService) myInvocationHandler.getProxy();
        UserService userService2 = (UserService) myInvocationHandler.getProxy();
        userService.insert();
        userService.delete();
        userService.insertOrUpdate();
//
//        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
//                "", [UserService.class]);

    }
}


/**
 * 实现自己的InvocationHandler
 * @author zyb
 * @since 2012-8-9
 *
 */
class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    /**
     * 构造方法
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    /**
     * 执行目标对象的方法
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("------------------after-------------------");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}

interface UserService {

    void insert();
    void delete();
    void insertOrUpdate();
}


/**
 * 目标对象
 * @author zyb
 * @since 2012-8-9
 *
 */
class UserServiceImpl implements UserService {

    public void insert() {
        System.out.println("--------------------insert---------------");
    }

    final public void delete() {
        System.out.println("--------------------delete---------------");
    }

    public void insertOrUpdate() {
        insert();
        update();
    }

    public void update() {
        System.out.println("--------------------update---------------");
    }
}