package org.ifunq.tanzx.spring.JDBC;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-27 22:25
 **/
public class JdbcMybatisTest04 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/JDBC/JDBC-Mybatis004.xml");
        System.out.println(Arrays.asList(context.getBeanDefinitionCount()));
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
        SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setSkuId(1006l);
        skuBarcodeDO.setBarcode("BK1106");
        skuBarcodeDO.setCreateBy("tanxianbiao");
        skuBarcodeDO.setModifiedBy("tanxianbiao");
        sqlSessionTemplate.insert("org.ifunq.tanzx.spring.JDBC.mybatis.dao.XXXXX.insert", skuBarcodeDO);
    }
}