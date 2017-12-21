package org.ifunq.tanzx.spring.FactoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义FactoryBean
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-14 14:27
 **/
public class MyFactoryBean implements FactoryBean {

    public Object getObject() throws Exception {
        MyRealBean myRealBean = new MyRealBean();
        return myRealBean;
    }

    public Class<?> getObjectType() {
        return MyRealBean.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void sayBean() {
        System.out.println("sayBean");
    }
}