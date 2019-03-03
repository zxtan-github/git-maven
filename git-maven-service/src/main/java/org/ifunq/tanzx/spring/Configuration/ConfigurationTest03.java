package org.ifunq.tanzx.spring.Configuration;

import org.ifunq.tanzx.spring.bean.ConfigurationBean003;
import org.ifunq.tanzx.spring.bean.SpringBean001;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest03 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBean003.class);
        System.out.println(context.getEnvironment().getProperty("appName"));
        System.out.println(context.getBean(SpringBean001.class).getName());
        System.out.println(ConfigurationBean003.author2);
        if (context.containsBean("getSpringBean002")) {
            System.out.println("Container contains SpringBean002");
        } else {
            System.out.println("Container do't contains SpringBean002");
        }
        if (context.containsBean("getSpringBean003")) {
            System.out.println("Container contains SpringBean003");
        } else {
            System.out.println("Container do't contains SpringBean003");
        }
    }
}
