package org.ifunq.tanzx.spring.FactoryBean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 引用FactoryBean
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-14 15:13
 **/
public class MyAutowiredFactoryBean {

    @Autowired
    MyRealBean myRealBean;

    @Autowired
    MyFactoryBean myFactoryBean;

    public void sayBeanByOther() {
        myRealBean.sayReal();
        myFactoryBean.sayBean();
    }

}