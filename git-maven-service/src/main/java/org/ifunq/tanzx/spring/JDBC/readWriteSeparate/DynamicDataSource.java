package org.ifunq.tanzx.spring.JDBC.readWriteSeparate;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-08 10:32
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final ThreadLocal<String> holder = new ThreadLocal<String>();
    @Override
    protected Object determineCurrentLookupKey() {
        return holder.get();
    }
}