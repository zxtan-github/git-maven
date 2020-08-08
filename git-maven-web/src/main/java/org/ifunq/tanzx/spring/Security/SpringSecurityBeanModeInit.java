package org.ifunq.tanzx.spring.Security;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

/**
 * 加载用户信息
 * ApplicationContext初始化完成以后，使用InMemoryUserDetailsManager加在用户信息
 *
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2020/8/8 18:24
 */
public class SpringSecurityBeanModeInit extends ApplicationObjectSupport {

    public void initApplicationContext() throws BeansException {
        InMemoryUserDetailsManager userDetailsService =
                (InMemoryUserDetailsManager) getApplicationContext().getBean("userDetailsService");
        userDetailsService.createUser(new User("user","user",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        userDetailsService.createUser(new User("admin","admin",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
        userDetailsService.createUser(new User("tanzongxi","123",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }
}
