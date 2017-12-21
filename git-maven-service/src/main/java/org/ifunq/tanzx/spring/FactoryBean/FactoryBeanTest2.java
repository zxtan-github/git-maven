package org.ifunq.tanzx.spring.FactoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试1
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-13 23:13
 **/
public class FactoryBeanTest2 {

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/FactoryBean/FactoryBean2.xml");
        MyAutowiredFactoryBean myAutowiredFactoryBean = (MyAutowiredFactoryBean) ctx.getBean("myAutowiredFactoryBean");
        myAutowiredFactoryBean.sayBeanByOther();
    }
}
