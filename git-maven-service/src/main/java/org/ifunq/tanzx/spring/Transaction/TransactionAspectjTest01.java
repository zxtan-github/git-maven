package org.ifunq.tanzx.spring.Transaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionAspectjTest01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/Transaction/TransactionAspectjTest01.xml");
        TransactionService service = (TransactionService) context.getBean("transactionService");
        try {
            service.insertSkuBarcodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
