package org.ifunq.tanzx.spring.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MethodInterceptor测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:04
 **/
public class ProxyFactoryBeanTest3 {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/AOP/AopProxyFactoryBean3.xml");
        CarService carService = (CarService) ctx.getBean("carService");
        carService.start();
    }
}