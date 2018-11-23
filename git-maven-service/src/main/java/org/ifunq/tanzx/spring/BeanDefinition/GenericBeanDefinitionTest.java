package org.ifunq.tanzx.spring.BeanDefinition;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.ifunq.tanzx.spring.bean.SpringBean002;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class GenericBeanDefinitionTest {

    public static void main(String[] args) {
        // 定义BeanFactory及BeanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition1 = new RootBeanDefinition();
        beanDefinition1.setBeanClassName("org.ifunq.tanzx.spring.bean.SpringBean001");
        BeanDefinition beanDefinition2 = new GenericBeanDefinition();
        beanDefinition2.setBeanClassName("org.ifunq.tanzx.spring.bean.SpringBean002");

        // BeanFactory注册BeanDefinition到容器中，并取beanName
        beanFactory.registerBeanDefinition("SpringBean00101", beanDefinition1);
        beanFactory.registerBeanDefinition("SpringBean00102", beanDefinition1);
        beanFactory.registerBeanDefinition("SpringBean00201", beanDefinition2);

        // 从BeanFactory获取bean，如果是第一次getBean就会生成对应实例
        SpringBean001 springBean00101 = (SpringBean001) beanFactory.getBean("SpringBean00101");
        SpringBean001 springBean00102 = (SpringBean001) beanFactory.getBean("SpringBean00102");
        SpringBean002 springBean00201 = (SpringBean002) beanFactory.getBean("SpringBean00201");

        // bean使用
        springBean00101.sayHello();
        springBean00102.sayHello();
        springBean00201.sayHi();

        // 判断同一个class类型的BeanDefinition是否相等的
        beanDefinition1 = beanFactory.getBeanDefinition("SpringBean00101");
        beanDefinition2 = beanFactory.getBeanDefinition("SpringBean00102");
        System.out.println("同一个class类型的BeanDefinition是相等");
        if(beanDefinition1 == beanDefinition2) {
            System.out.println("同一个class类型的BeanDefinition是相等");
        }
    }
}
