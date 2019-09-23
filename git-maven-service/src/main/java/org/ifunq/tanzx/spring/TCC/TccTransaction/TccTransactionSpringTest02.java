package org.ifunq.tanzx.spring.TCC.TccTransaction;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * tcc-transaction Try-Cancel异常示例
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2019/9/23 17:31
 */
public class TccTransactionSpringTest02 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/TCC/TccTransactionSpring02.xml");
        TccTransactionService02 service = context.getBean("tccTransactionService02", TccTransactionService02.class);
        System.out.println(service.record());
    }
}


class TccTransactionService02 {
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", asyncConfirm = false)
    @Transactional
    public String record() {
        System.out.println("tryRecord");
        throw new NullPointerException();
        // return "success";
    }
    public void confirmRecord() {
        System.out.println("confirmRecord");
    }
    public void cancelRecord() {
        System.out.println("cancelRecord");
    }
}
