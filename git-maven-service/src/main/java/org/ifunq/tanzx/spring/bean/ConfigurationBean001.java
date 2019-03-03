package org.ifunq.tanzx.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * ConfigurationBean001被@Import导入以后，可以不再需要@Configuration注解
 */
@ComponentScan("org.ifunq.tanzx.spring.Controller")
public class ConfigurationBean001 {

    @Bean
    public SpringBean001 getSpringBean001() {
        return new SpringBean001();
    }

    /**
     * 无返回值方法使用@Bean标签会报错
     * Invalid factory method 'getWithoutResult': needs to have a non-void return type!
     */
//    @Bean
//    public void getWithoutResult() {
//    }

    /**
     * 返回基本型@Bean不会报错
     * @return
     */
    @Bean
    public int getWithBaseResult() {
        return 3;
    }
}
