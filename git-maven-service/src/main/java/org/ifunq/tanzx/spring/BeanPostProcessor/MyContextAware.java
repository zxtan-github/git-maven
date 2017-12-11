package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 实现ApplicationContextAware的Bean
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-10 11:19
 **/
public class MyContextAware implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void getOtherBeanSayHello () {
        SpringBean001 springBean001 = (SpringBean001) applicationContext.getBean("myContextAware");
        springBean001.sayHello();
    }
}