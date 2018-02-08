package org.ifunq.tanzx.spring.JDBC;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2018-02-08 16:01
 **/
public class JdbcReadWriteSeparateTest02 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/JDBC/JDBC-ReadWriteSeparate02.xml");
        SkuBarcodeDao skuBarcodeDao = (SkuBarcodeDao) context.getBean("skuBarcodeDao");
        // 读库检索
        SkuBarcodeDO skuBarcodeDO = skuBarcodeDao.getById(2l);
        System.out.println(skuBarcodeDO);
        skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setId(2l);
        skuBarcodeDO.setBarcode("9999888");
        // 写库更新
        skuBarcodeDao.update(skuBarcodeDO);
        // 读库再检索（未发生更新，因为没有同步）
        skuBarcodeDO = skuBarcodeDao.getById(2l);
        System.out.println(skuBarcodeDO);
    }
}