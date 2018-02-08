package org.ifunq.tanzx.spring.JDBC.readWriteSeparate;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 对Dao方法的MyDataSource注解进行判断，设定写数据源还是读数据源
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-08 15:36
 **/
public class MyDataSourceAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        MyDataSource myDataSource = method.getAnnotation(MyDataSource.class);
        // 没有MyDataSource的注解使用写库
        if (myDataSource == null) {
            DynamicDataSource.holder.set("DEFAULT");
            return;
        }
        System.out.println("MyDataSource" + myDataSource);
        // 如果设定写库，否则读库
        if ("write".equals(myDataSource.value())) {
            DynamicDataSource.holder.set("DEFAULT");
        } else {
            DynamicDataSource.holder.set("READ_ONLY");
        }
    }
}