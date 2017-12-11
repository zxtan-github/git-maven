package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * 测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-10 11:37
 **/
public class MyMessageAwareTest3 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/BeanPostProcessor/AwareBean3.xml");
        // 国际化获取Message
        MyMessageAware myMessageAware = (MyMessageAware) context.getBean("myMessageAware");
        myMessageAware.sayMessage("message", null, null);
        myMessageAware.sayMessage("message", null, Locale.US);
        myMessageAware.sayMessage("message", null, Locale.CHINA);
    }
}