package org.ifunq.tanzx.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

/**
 * sdfg
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-21 15:53
 **/
public class SimpleSpringBean01 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String msg = "SimpleSpringBean01 msg ...";

    public void sayMsg(String msg) {
        System.out.println();
    }

    /**
     * 打印出Application的相关变量
     */
    public void getApplicationInfo () {
        System.out.println(applicationContext.getDisplayName());
        System.out.println(applicationContext.getBeanDefinitionCount());
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
}