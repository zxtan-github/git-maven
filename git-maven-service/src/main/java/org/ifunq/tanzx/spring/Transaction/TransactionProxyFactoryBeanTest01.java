package org.ifunq.tanzx.spring.Transaction;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionProxyFactoryBeanTest01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/Transaction/TransactionProxyFactoryBeanTest01.xml");
        TransactionService service = (TransactionService) context.getBean("transactionService");
        try {
            // insertSkuBarcodes会有异常，insertSkuBarcode没有
            service.insertSkuBarcodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TransactionService {

    @Autowired
    SkuBarcodeDao skuBarcodeDao;

    @Autowired
    OtherService otherService;

    public void insertSkuBarcodes() throws Exception {
        // 插入一条主键不存在的数据，能成功
        SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setId(102l);
        skuBarcodeDao.insert(skuBarcodeDO);
        // 不同service子事务进行
        otherService.insertSkuBarcode();
        // 同一service子事务每次新建事务不成功
        insertSkuBarcode();
        // 插入一条主键存在的数据，会失败
        skuBarcodeDO.setId(3l);
        skuBarcodeDao.insert(skuBarcodeDO);
    }

    public void insertSkuBarcode(){
        // 插入一条主键不存在的数据，能成功
        SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setId(105l);
        skuBarcodeDao.insert(skuBarcodeDO);
    }
}

class OtherService {

    @Autowired
    SkuBarcodeDao skuBarcodeDao;

    public void insertSkuBarcode(){
        // 插入一条主键不存在的数据，能成功
        SkuBarcodeDO skuBarcodeDO = new SkuBarcodeDO();
        skuBarcodeDO.setId(103l);
        skuBarcodeDao.insert(skuBarcodeDO);
    }
}
