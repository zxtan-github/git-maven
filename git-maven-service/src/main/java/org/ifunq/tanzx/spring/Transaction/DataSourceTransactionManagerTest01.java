package org.ifunq.tanzx.spring.Transaction;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class DataSourceTransactionManagerTest01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/Transaction/DataSourceTransactionManagerTest01.xml");
        SkuBarcodeDao skuBarcodeDao = (SkuBarcodeDao) context.getBean("skuBarcodeDao");
        DataSourceTransactionManager transactionManager =  (DataSourceTransactionManager) context.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 插入一条主键不存在的数据，能成功
            SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
            skuBarcodeDO.setId(100l);
            skuBarcodeDO.setSkuId(100212l);
            skuBarcodeDO.setBarcode("BK1102");
            skuBarcodeDO.setCreateBy("tanzongxi");
            skuBarcodeDO.setModifiedBy("tanzongxi");
            skuBarcodeDao.insert(skuBarcodeDO);
            // 插入一条主键存在的数据，会失败
            skuBarcodeDO.setId(2l);
            skuBarcodeDao.insert(skuBarcodeDO);
            transactionManager.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            //会回滚，操作都不会成功
            transactionManager.rollback(status);
        }

    }
}
