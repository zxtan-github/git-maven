package org.ifunq.tanzx.spring.AOP.MethodInterceptor;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.adapter.AdvisorAdapter;
import org.springframework.aop.framework.adapter.UnknownAdviceTypeException;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:36
 **/
public class MyAdvisorAdapter implements AdvisorAdapter {
    public boolean supportsAdvice(Advice advice) {
        // 自定义Advisor适配器支持MyAdvice1、MyAdvice1两种
        if (advice instanceof MyAdvice1) return true;
        if (advice instanceof MyAdvice2) return true;
        return false;
    }

    public MethodInterceptor getInterceptor(Advisor advisor) {
        Advice advice = advisor.getAdvice();
        // 如果是MyAdvice1，就把返回MyAdviceInterceptor1
        if (advice instanceof MyAdvice1)
            return new MyAdviceInterceptor1((MyAdvice1)advice);
        // 如果是MyAdvice2，就把返回MyAdviceInterceptor2
        if (advice instanceof MyAdvice2)
            return new MyAdviceInterceptor2((MyAdvice2)advice);
        // 都不是可以抛错
        throw new UnknownAdviceTypeException(advice);
    }
}