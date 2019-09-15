package org.ifunq.tanzx.spring.Configuration;

import org.ifunq.tanzx.spring.bean.ConfigurationBean004;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;

@Deprecated
@Description("ConfigurationBean004")
@Configuration
@Import(ConfigurationBean004.class)
public class ConfigurationTest04 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationTest04.class);
    }
}
