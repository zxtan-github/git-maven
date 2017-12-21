package org.ifunq.tanzx.spring.AOP.MethodInterceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义MethodInterceptor1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:02
 **/
public class MyAdviceInterceptor1 implements MethodInterceptor {

    MyAdvice1 myAdvice1;

    public MyAdviceInterceptor1(MyAdvice1 myAdvice1) {
        this.myAdvice1 = myAdvice1;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        myAdvice1.before();
        Object result =  invocation.proceed();
        myAdvice1.after();
        return result;
    }
}