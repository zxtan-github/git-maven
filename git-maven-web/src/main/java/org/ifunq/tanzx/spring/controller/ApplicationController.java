package org.ifunq.tanzx.spring.controller;

import org.ifunq.tanzx.spring.bean.SimpleSpringBean01;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 总控制器
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-21 15:43
 **/

@Controller
@RequestMapping("/mvc")
public class ApplicationController implements ApplicationContextAware{

    @Resource
    private SimpleSpringBean01 simpleSpringBean01;

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/hello")
    public String hello(){
        simpleSpringBean01.getApplicationInfo();
        this.getApplicationInfo();
        return "hello";
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