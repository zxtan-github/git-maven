package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-10 11:37
 **/
public class MyMessageAwareTest2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/BeanPostProcessor/AwareBean3.xml");
        // MessageSourceAware获取Message
        MyMessageAware myMessageAware = (MyMessageAware) context.getBean("myMessageAware");
        myMessageAware.sayMessage("message");
        myMessageAware.sayMessage("argument.required", new String[] {"tanzongxi"}, null);
        // ApplicationContext直接获取Message
        System.out.println(context.getMessage("message", null, null));
        System.out.println(context.getMessage("argument.required", new String[] {"tanzongxi"}, null));
    }
}