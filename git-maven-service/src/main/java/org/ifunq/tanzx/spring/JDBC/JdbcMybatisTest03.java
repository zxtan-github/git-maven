package org.ifunq.tanzx.spring.JDBC;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeImplDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-27 22:25
 **/
public class JdbcMybatisTest03 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/JDBC/JDBC-Mybatis003.xml");
        System.out.println(Arrays.asList(context.getBeanDefinitionCount()));
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        SkuBarcodeImplDao skuBarcodeImplDao = (SkuBarcodeImplDao) context.getBean("skuBarcodeDao");
        SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setSkuId(1005l);
        skuBarcodeDO.setBarcode("BK1105");
        skuBarcodeDO.setCreateBy("tanruoxi");
        skuBarcodeDO.setModifiedBy("tanruoxi");
        skuBarcodeImplDao.insert(skuBarcodeDO);
    }
}