package org.ifunq.tanzx.spring.AOP.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 使用spring的AOP机制的事前通知接口实现
 * @author fruitking
 * @since 2010-02-23
 */
public class CarServiceAroundAdvice implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before around excute target object...");
        String methodName = invocation.getMethod().getName();  //得到方法名
        String targetClassName = invocation.getClass().getName();//得到调用类名
        System.out.println(targetClassName+"."+methodName+"()");
        Object result = invocation.proceed(); //调用横切点，即真实操作
        System.out.println("after around excute target object...");
        return result;
    }
}