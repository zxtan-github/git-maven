package org.ifunq.tanzx.spring.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-13 23:13
 **/
public class ProxyFactoryBeanTest2 {

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/AOP/AopProxyFactoryBean2.xml");
        CarService carService = (CarService) ctx.getBean("carService");
        carService.start();
    }
}
