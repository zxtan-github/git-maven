package org.ifunq.tanzx.spring.BeanDefinition;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class AnnotatedGenericBeanDefinitionTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(AnnotatedClass.class);
        beanFactory.registerBeanDefinition("annotatedClass", beanDefinition);
        AnnotatedClass annotatedClass = (AnnotatedClass) beanFactory.getBean("annotatedClass");
        annotatedClass.sayAny();
    }
}
class AnnotatedClass {
    public void sayAny() {
        System.out.println("AnnotatedClass sayAny...");
    }
}