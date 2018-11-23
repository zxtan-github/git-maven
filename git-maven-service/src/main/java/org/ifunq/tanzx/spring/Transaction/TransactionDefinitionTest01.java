package org.ifunq.tanzx.spring.Transaction;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionDefinitionTest01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/Transaction/DataSourceTransactionManagerTest01.xml");
        SkuBarcodeDao skuBarcodeDao = (SkuBarcodeDao) context.getBean("skuBarcodeDao");
        DataSourceTransactionManager transactionManager =  (DataSourceTransactionManager) context.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status1 = transactionManager.getTransaction(def);
        try {
            SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
            skuBarcodeDO.setId(3001l);
            skuBarcodeDao.insert(skuBarcodeDO);
            TransactionStatus status2 = transactionManager.getTransaction(def);
            skuBarcodeDO.setId(3002l);
            skuBarcodeDao.insert(skuBarcodeDO);
            transactionManager.rollback(status2);
            transactionManager.commit(status1);
            // 只需提交主事务status1，就可以全部提交，只提交子事务status2没有效果
//            transactionManager.commit(status1);
        } catch (Exception e) {}
    }


}
