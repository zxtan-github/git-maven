package org.ifunq.tanzx.spring.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("spring/Configuration/Configuration4.properties")
@Component
public class ConfigurationBean003 {

    // 将从Configuration4.properties取得到的author属性值设定给author属性
    @Value("${author}")
    public String author;

    @Bean
    public SpringBean001 getSpringBean001() {
        SpringBean001 springBean001 = new SpringBean001();
        // 将从Configuration4.properties取得到的author属性值设定name
        springBean001.setName(author);
        return springBean001;
    }

    @Conditional(MyCondition02.class)
    @Bean
    public SpringBean002 getSpringBean002() {
        return new SpringBean002();
    }

    @Conditional(MyCondition03.class)
    @Bean
    public SpringBean003 getSpringBean003() {
        return new SpringBean003();
    }

    @Value("#{configurationBean003.author}")
    static public String author2;
}
