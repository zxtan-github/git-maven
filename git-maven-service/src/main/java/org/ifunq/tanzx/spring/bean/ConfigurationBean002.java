package org.ifunq.tanzx.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ConfigurationBean001.class, SpringBean003.class})
public class ConfigurationBean002 {

    /**
     * Bean标签引入参数时，使用class引入方式，context.getBean(SpringBean001.class)
     * @param springBean001
     * @return
     */
    @Bean
    public SpringBean002 getSpringBean002(SpringBean001 springBean001) {
        springBean001.sayHello();
        return new SpringBean002();
    }
}
