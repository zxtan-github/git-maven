package org.ifunq.tanzx.spring.TCC.TccTransaction;

import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.spring.support.SpringTransactionConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * tcc-transaction Try-Confirm正常示例
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2019/9/23 17:31
 */
public class TccTransactionSpringTest01 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/TCC/TccTransactionSpring01.xml");
        TransactionRepository transactionRepository = context.getBean("transactionRepository", TransactionRepository.class);
        SpringTransactionConfigurator springTransactionConfigurator = context.getBean("transactionConfigurator", SpringTransactionConfigurator.class);
        springTransactionConfigurator.getTransactionManager().setTransactionRepository(transactionRepository);
        TccTransactionService01 service = context.getBean("tccTransactionService01", TccTransactionService01.class);
        System.out.println(service.record());
    }
}

class TccTransactionService01 {
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord")
    @Transactional
    public String record() {
        System.out.println("tryRecord");
         return "success";
    }
    public void confirmRecord() {
        System.out.println("confirmRecord");
    }
    public void cancelRecord() {
        System.out.println("cancelRecord");
    }
}
