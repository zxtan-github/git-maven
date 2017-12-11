package org.ifunq.tanzx.spring.Autowired;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动注入Bean
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 11:13
 **/
public class AutowiredBean1 {

    @Autowired
    SpringBean001 springBean001;

    public void getOtherBeanSayHello () {
        springBean001.sayHello();
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/Autowired/Autowired1.xml");
        AutowiredBean1 autowiredBean1 = (AutowiredBean1) context.getBean("autowiredBean1");
        autowiredBean1.getOtherBeanSayHello();
    }
}