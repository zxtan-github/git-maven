package org.ifunq.tanzx.spring.Autowired;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 自动注入Bean
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 11:13
 **/
public class AutowiredBeanFactoryTest2 {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("spring/Autowired/Autowired2.xml");
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
        // 添加AutowiredAnnotationBeanPostProcessor
        AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 由于AutowiredAnnotationBeanPostProcessor要使用BeanFactory，所以要把引用传进去
        annotationBeanPostProcessor.setBeanFactory(xmlBeanFactory);
        xmlBeanFactory.addBeanPostProcessor(annotationBeanPostProcessor);
        AutowiredBean1 autowiredBean1 = (AutowiredBean1) xmlBeanFactory.getBean("autowiredBean1");
        autowiredBean1.getOtherBeanSayHello();
    }
}