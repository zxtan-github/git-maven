package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.ifunq.tanzx.spring.Autowired.AutowiredBean1;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class AutowiredAnnotationBeanPostProcessorTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition1 = new GenericBeanDefinition();
        beanDefinition1.setBeanClassName("org.ifunq.tanzx.spring.bean.SpringBean001");
        BeanDefinition beanDefinition2 = new GenericBeanDefinition();
        beanDefinition2.setBeanClassName("org.ifunq.tanzx.spring.Autowired.AutowiredBean1");
        beanFactory.registerBeanDefinition("springBean001", beanDefinition1);
        beanFactory.registerBeanDefinition("autowiredBean1", beanDefinition2);

        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(processor);

        AutowiredBean1 autowiredBean1 = (AutowiredBean1) beanFactory.getBean("autowiredBean1");
        autowiredBean1.getOtherBeanSayHello();


    }
}


