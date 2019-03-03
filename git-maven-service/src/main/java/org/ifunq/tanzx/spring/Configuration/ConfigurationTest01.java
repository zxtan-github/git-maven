package org.ifunq.tanzx.spring.Configuration;

import org.ifunq.tanzx.spring.Controller.SampleController;
import org.ifunq.tanzx.spring.bean.ConfigurationBean002;
import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.ifunq.tanzx.spring.bean.SpringBean002;
import org.ifunq.tanzx.spring.bean.SpringBean003;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBean002.class);
        // @Bean基本型照样可以获取
        Integer getWithBaseResult = (Integer) context.getBean("getWithBaseResult");
        System.out.println(getWithBaseResult);
        SpringBean001 springBean001 = context.getBean(SpringBean001.class);
        springBean001.sayHello();
        SpringBean002 springBean002 = context.getBean(SpringBean002.class);
        springBean002.sayHi();
        SpringBean003 springBean003 = context.getBean(SpringBean003.class);
        springBean003.sayNihao();
        SampleController sampleController = context.getBean(SampleController.class);
        sampleController.addPrint();

    }
}
