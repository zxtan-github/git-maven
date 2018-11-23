package org.ifunq.tanzx.spring.Remote;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRmiServer01 {

    public static void main(String[] args) {
        // RMI不是http请求，是基于TCP/IP，不需要spring web包
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/Remote/spring-rmi-server01.xml");
    }
}
