package org.ifunq.tanzx.spring.AOP.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 使用spring的AOP机制的事前通知接口实现
 * 
 * @author fruitking
 * @since 2010-02-23
 */
public class CarServiceAfterAdvice implements AfterReturningAdvice {

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
            throws Throwable {
        String methodName = method.getName(); // 得到方法名
        String targetClassName = target.getClass().getName();// 得到调用类名
        System.out.println(targetClassName + "." + methodName + "()");
        System.out.println("after excute target object...");
    }
}
