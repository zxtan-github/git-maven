package org.ifunq.tanzx.spring.AOP.MethodInterceptor;

import org.aopalliance.aop.Advice;

/**
 * MyAdvice1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:28
 **/
public class MyAdvice2 implements Advice {

    public void before () {
        System.out.println("MyAdvice2 before...");
    }

    public void after () {
        System.out.println("MyAdvice2 after...");
    }
}