package org.ifunq.tanzx.spring.Security;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
/**
 * 自定义的AuthenticationManager，直接完成认证
 *
 * @author tanzongxi [tanzongxi@ifunq.com]
 * @date 2020/8/8 15:03
 */
public class SpringSecurityCustomAuthenticationManager implements AuthenticationManager, MessageSourceAware {

    @Override
    public void setMessageSource(MessageSource messageSource) {
    }

    /**
     * 自定义的AuthenticationManager，替换了默认的org.springframework.security.authentication.ProviderManager
     * 不需要依赖任何AuthenticationProvider（默认DaoAuthenticationProvider）和UserDetailsService（默认InMemoryUserDetailsManager）
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        // 由于访问限制需要角色，吧角色添加进去，权限认证是另外一个Filter做的
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(), authorities);
        return result;
    }

    /**
     * 对应默认Filter中调用了这个方法，必须添加上去
     * 除非用自定义Filter替换调默认Filter
     *
     * @return
     */
    public boolean isEraseCredentialsAfterAuthentication() {
        return true;
    }
}
