package org.ifunq.tanzx.spring.JDBC;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-27 22:25
 **/
public class JdbcMybatisTest02 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/JDBC/JDBC-Mybatis002.xml");
        System.out.println(Arrays.asList(context.getBeanDefinitionCount()));
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        SkuBarcodeDao skuBarcodeDao = (SkuBarcodeDao) context.getBean("skuBarcodeDao");
        SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setSkuId(1002l);
        skuBarcodeDO.setBarcode("BK1102");
        skuBarcodeDO.setCreateBy("tanzongxi");
        skuBarcodeDO.setModifiedBy("tanzongxi");
        skuBarcodeDao.insert(skuBarcodeDO);
    }
}