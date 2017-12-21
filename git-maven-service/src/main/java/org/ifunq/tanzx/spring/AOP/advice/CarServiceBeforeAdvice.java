package org.ifunq.tanzx.spring.AOP.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 使用spring的AOP机制的事前通知接口实现
 * 
 * @author fruitking
 * @since 2010-02-23
 */
public class CarServiceBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before excute target object...");
        String methodName = method.getName(); // 得到方法名
        String targetClassName = target.getClass().getName();// 得到调用类名
        System.out.println(targetClassName + "." + methodName + "()");
    }
}
