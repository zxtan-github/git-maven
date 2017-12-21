package org.ifunq.tanzx.spring.AOP;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * MethodInterceptor测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 17:04
 **/
public class ProxyFactoryBeanTest4 {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("spring/AOP/AopProxyFactoryBean4.xml");
        XmlBeanFactory ctx=new XmlBeanFactory(resource);
        CarService carService = (CarService) ctx.getBean("carService");
        carService.start();
    }
}