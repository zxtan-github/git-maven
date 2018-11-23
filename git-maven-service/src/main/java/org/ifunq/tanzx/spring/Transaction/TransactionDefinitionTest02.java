package org.ifunq.tanzx.spring.Transaction;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2018/11/19 11:40
 */
public class TransactionDefinitionTest02 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/Transaction/DataSourceTransactionManagerTest01.xml");
        SkuBarcodeDao skuBarcodeDao = (SkuBarcodeDao) context.getBean("skuBarcodeDao");
        DataSourceTransactionManager transactionManager =  (DataSourceTransactionManager) context.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status1 = transactionManager.getTransaction(def);
        try {
            SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
            skuBarcodeDO.setId(3001l);
            skuBarcodeDao.insert(skuBarcodeDO);
            TransactionStatus status2 = transactionManager.getTransaction(def);
            skuBarcodeDO.setId(3002l);
            skuBarcodeDao.insert(skuBarcodeDO);
            transactionManager.commit(status2);
            skuBarcodeDO.setId(3l);
            skuBarcodeDao.insert(skuBarcodeDO);
//            transactionManager.rollback(status1);
        } catch (Exception e) {e.printStackTrace();}
    }
}
