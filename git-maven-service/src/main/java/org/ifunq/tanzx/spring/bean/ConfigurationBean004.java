package org.ifunq.tanzx.spring.bean;

import org.springframework.context.annotation.*;

@PropertySource("spring/Configuration/Configuration4.properties")
public class ConfigurationBean004 {
    @Bean
    public SpringBean002 getSpringBean002() {
        return new SpringBean002();
    }
}
