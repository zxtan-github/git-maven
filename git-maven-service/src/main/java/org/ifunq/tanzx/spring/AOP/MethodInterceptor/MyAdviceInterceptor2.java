package org.ifunq.tanzx.spring.AOP.MethodInterceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义MethodInterceptor1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:02
 **/
public class MyAdviceInterceptor2 implements MethodInterceptor {

    MyAdvice2 myAdvice2;

    public MyAdviceInterceptor2(MyAdvice2 myAdvice2) {
        this.myAdvice2 = myAdvice2;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        myAdvice2.before();
        Object result =  invocation.proceed();
        myAdvice2.after();
        return result;
    }
}