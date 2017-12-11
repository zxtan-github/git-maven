package org.ifunq.tanzx.spring.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * 001
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-07 17:16
 **/
public class SpringBean004 {

    public void sayKoniqiwa() {
        System.out.println(" SpringBean004 sayKoniqiwa...");
    }

    public void sayBeanKoniqiwa() {
        BeanKoniqiwa beanKoniqiwa = (BeanKoniqiwa) this.getBean();
        beanKoniqiwa.sayBeanKoniqiwa();
    }

    public Object getBean() {
      return "CX";
    }
}


