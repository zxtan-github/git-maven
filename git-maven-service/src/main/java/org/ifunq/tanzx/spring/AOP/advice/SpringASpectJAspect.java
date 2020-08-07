package org.ifunq.tanzx.spring.AOP.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.ifunq.tanzx.spring.JDBC.readWriteSeparate.MyDataSource;

/**
 * ASpectJ注解切面类
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-09 9:51
 **/
@Aspect
public class SpringASpectJAspect {

    /**
     * 这个方法就相当于生产一个切点Point，这个切点会和下面所有的Advice一起组成Advisor
     */
    @Pointcut("execution(* org.ifunq.tanzx.spring.AOP.Car*.*(..))")
    public void aspectjPoint() {
    }

    /**
     * 这个方法就相当于生产一个org.springframework.aop.aspectj.AspectJMethodBeforeAdvice
     * 和MethodBeforeAdvice类似，会和上面的Point一起组成Advisor
     */
    @Before("aspectjPoint()")
    public void before(JoinPoint point) {
        System.out.println("ASpectJ前置通知..." + ((MethodSignature) point.getSignature()).getMethod().getAnnotation(MyDataSource.class));
    }

    /**
     * 这个方法就相当于生产一个org.springframework.aop.aspectj.AspectJAfterReturningAdvice
     * 和MethodBeforeAdvice类似，会和上面的Point一起组成Advisor
     */
    @AfterReturning("aspectjPoint()")
    public void after() {
        System.out.println("ASpectJ后置通知");
    }

    /**
     * 这个方法就相当于生产一个org.springframework.aop.aspectj.AspectJAroundAdvice
     * 会和上面的Point一起组成Advisor
     */
    @Around("aspectjPoint()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("ASpectJ环绕前置增强..." + ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(MyDataSource.class));
        Object o = proceedingJoinPoint.proceed();
        System.out.println("ASpectJ环绕后置增强...");
        return o;
    }
}