package org.ifunq.tanzx.spring.AOP.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * 使用spring的AOP机制的事前通知接口实现
 * @author fruitking
 * @since 2010-02-23
 */
public class CarServiceThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(NullPointerException e){//可以定义多个方法，只要传入的参数是不同异常
        System.out.print("not load anything goods!");
    }

    public void afterThrowing(IllegalArgumentException e){//可以定义多个方法，只要传入的参数是不同异常
        System.out.print("load a tiger,it's very much dangerous!");
    }

}