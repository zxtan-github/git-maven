package org.ifunq.tanzx.spring.AOP.MethodInterceptor.other;

import org.ifunq.tanzx.spring.AOP.MethodInterceptor.MyAdvisorAdapter;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-15 18:51
 **/
public class MyAdvisorAdapterRegistry extends DefaultAdvisorAdapterRegistry {
    public MyAdvisorAdapterRegistry () {
        super();
        this.registerAdvisorAdapter(new MyAdvisorAdapter());
    }
}