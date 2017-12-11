package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

/**
 * 测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-10 11:37
 **/
public class MyMessageAwareTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/BeanPostProcessor/AwareBean2.xml");
        StaticMessageSource messageSource = (StaticMessageSource) context.getBean("messageSource");
        // 编程式添加Message
        messageSource.addMessage("key_001", Locale.CHINA, "中国人民");
        // MessageSourceAware获取Message
        MyMessageAware myMessageAware = (MyMessageAware) context.getBean("myMessageAware");
        myMessageAware.sayMessage("key_001");
        // ApplicationContext直接获取Message
        System.out.println(context.getMessage("key_001", null, null));
    }
}