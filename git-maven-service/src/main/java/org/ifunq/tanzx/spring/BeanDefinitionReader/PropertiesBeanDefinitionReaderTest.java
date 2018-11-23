package org.ifunq.tanzx.spring.BeanDefinitionReader;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

import java.util.Arrays;

public class PropertiesBeanDefinitionReaderTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader definitionReader = new PropertiesBeanDefinitionReader(beanFactory) ;
        definitionReader.loadBeanDefinitions("spring/BeanDefinitionReader/SimplePropertiesBean.properties");
        System.out.println(Arrays.asList(beanFactory.getBeanDefinitionNames()));
        SpringBean001 bean001 = (SpringBean001) beanFactory.getBean("propBean");
        bean001.sayHello();
        System.out.println("name-->"+bean001.name);
        System.out.println("ClassLoader-->"+bean001.getClass().getClassLoader());
    }
}
