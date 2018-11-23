package org.ifunq.tanzx.spring.Remote;

import org.ifunq.tanzx.spring.bean.User;
import org.ifunq.tanzx.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRmiClient01 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/Remote/spring-rmi-client01.xml");
        UserService us = (UserService) ac.getBean("userService");
        User user = us.getOneUser();
        System.out.println(user);
    }
}
