package org.ifunq.tanzx.spring.Security;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

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
