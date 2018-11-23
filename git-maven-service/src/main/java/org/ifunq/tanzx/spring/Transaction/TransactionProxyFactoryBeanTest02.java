package org.ifunq.tanzx.spring.Transaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionProxyFactoryBeanTest02 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/Transaction/TransactionProxyFactoryBeanTest02.xml");
        TransactionService service = (TransactionService) context.getBean("transactionService");
        try {
            // insertSkuBarcodes会有异常，insertSkuBarcode没有
            service.insertSkuBarcodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
