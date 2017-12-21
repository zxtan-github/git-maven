package org.ifunq.tanzx.spring.FactoryBean;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-13 23:13
 **/
public class FactoryBeanTest1 {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("spring/FactoryBean/FactoryBean1.xml");
        XmlBeanFactory ctx=new XmlBeanFactory(resource);
        // 并根据id获取的bean是getObeject返回的实例
        MyRealBean myRealBean = (MyRealBean) ctx.getBean("myFactoryBean");
        myRealBean.sayReal();
        // 并根据FactoryBean的自身实例在id前面加一个&
        MyFactoryBean myFactoryBean = (MyFactoryBean) ctx.getBean("&myFactoryBean");
        myFactoryBean.sayBean();
        // 直接获取MyFactoryBean类型报错
        myFactoryBean = (MyFactoryBean) ctx.getBean("myFactoryBean");
    }
}
