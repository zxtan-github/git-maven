package org.ifunq.tanzx.spring.Transaction;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionTemplateTest01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/Transaction/TransactionTemplateTest01.xml");
        final SkuBarcodeDao skuBarcodeDao = (SkuBarcodeDao) context.getBean("skuBarcodeDao");
        TransactionTemplate transactionTemplate =  (TransactionTemplate) context.getBean("transactionTemplate");
        try {
            Object result = transactionTemplate.execute(
                    new TransactionCallback<Object>(){
                        public Object doInTransaction(TransactionStatus status){
                            // 插入一条主键不存在的数据，能成功
                            SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
                            skuBarcodeDO.setId(101l);
                            skuBarcodeDao.insert(skuBarcodeDO);
                            // 插入一条主键存在的数据，会失败
                            skuBarcodeDO.setId(2l);
                            skuBarcodeDao.insert(skuBarcodeDO);
                            return "success";
                        }
                    }); //执行方法进行事务管理
            System.out.println(result);
        } catch (TransactionException e) {
            e.printStackTrace();
        }

    }
}
