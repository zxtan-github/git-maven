package org.ifunq.tanzx.spring.AOP.MethodInterceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义MethodInterceptor1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:02
 **/
public class MyMethodInterceptor1 implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("MyMethodInterceptor1 before...");
        Object result =  invocation.proceed();
        System.out.println("MyMethodInterceptor1 after...");
        return result;
    }
}