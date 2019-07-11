package org.ifunq.tanzx.spring.xmlbeanfactory;

import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-08 17:23
 **/
public class XmlBeanFatoryTest {
    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("spring/SimpleXmlBean.xml");
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(classPathResource);
        SpringBean001 springBean001 = (SpringBean001) xmlBeanFactory.getBean("bean001");
        springBean001.sayHello();
//        SpringBean002 springBean002 = (SpringBean002) xmlBeanFactory.getBean("bean002");
//        springBean002.sayHi();
    }
}