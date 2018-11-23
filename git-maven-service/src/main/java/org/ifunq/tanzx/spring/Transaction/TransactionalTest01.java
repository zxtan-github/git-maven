package org.ifunq.tanzx.spring.Transaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionalTest01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/Transaction/TransactionalTest01.xml");
        TransactionService service = (TransactionService) context.getBean("transactionService");
        try {
            service.insertSkuBarcodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
