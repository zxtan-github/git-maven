package org.ifunq.tanzx.spring.Remote;

import org.ifunq.tanzx.spring.bean.User;
import org.ifunq.tanzx.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHessianClient01 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/Remote/spring-hessian-client01.xml");
        UserService us = (UserService) ac.getBean("httpInvokerUserService");
        User user = us.getOneUser();
        System.out.println(user);
    }
}
