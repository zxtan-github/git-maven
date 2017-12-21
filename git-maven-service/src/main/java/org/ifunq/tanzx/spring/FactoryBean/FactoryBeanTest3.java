package org.ifunq.tanzx.spring.FactoryBean;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 测试1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-13 23:13
 **/
public class FactoryBeanTest3 {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(postProcessor);
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions("spring/FactoryBean/FactoryBean3.xml");
//        MyRealBean myRealBean = (MyRealBean) beanFactory.getBean("myFactoryBean");
//        myRealBean.sayReal();
        MyAutowiredFactoryBean myAutowiredFactoryBean = (MyAutowiredFactoryBean) beanFactory.getBean("myAutowiredFactoryBean");
        myAutowiredFactoryBean.sayBeanByOther();
    }
}
