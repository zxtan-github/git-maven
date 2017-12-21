package org.ifunq.tanzx.spring.AOP.MethodInterceptor;

import org.aopalliance.aop.Advice;

/**
 * MyAdvice1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:28
 **/
public class MyAdvice1 implements Advice {

    public void before () {
        System.out.println("MyAdvice1 before...");
    }

    public void after () {
        System.out.println("MyAdvice1 after...");
    }

}