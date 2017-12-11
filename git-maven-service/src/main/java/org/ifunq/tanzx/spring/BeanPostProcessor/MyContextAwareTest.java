package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-10 11:37
 **/
public class MyContextAwareTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/BeanPostProcessor/AwareBean1.xml");
        MyContextAware myContextAware = (MyContextAware) context.getBean("myContextAware");
        myContextAware.getOtherBeanSayHello();
    }
}