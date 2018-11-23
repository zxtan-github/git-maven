package org.ifunq.tanzx.spring.ApplicationContext;

import org.ifunq.tanzx.spring.bean.BeanKoniqiwa;
import org.ifunq.tanzx.spring.bean.SpringBean004;
import org.ifunq.tanzx.spring.bean.SpringBean005;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测是
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-08 23:10
 **/
public class ClassPathXmlApplicationTest {

    public static void main(String[] args) {
        // 方法重写
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/SimpleXmlBean3.xml");
        SpringBean004 springBean004 = (SpringBean004) context.getBean("bean004");
        springBean004.sayBeanKoniqiwa();
        springBean004.sayKoniqiwa();

        // 方法实现
        SpringBean005 springBean005 = (SpringBean005) context.getBean("bean005");
        BeanKoniqiwa beanKoniqiwa = (BeanKoniqiwa) springBean005.getBean();
        beanKoniqiwa.sayBeanKoniqiwa();
        springBean005.sayKoniqiwa();
    }

}

