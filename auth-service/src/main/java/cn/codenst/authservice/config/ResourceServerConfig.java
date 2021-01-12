package cn.codenst.authservice.config;

import cn.codenst.authservice.handler.MyAuthenticationFailureHandler;
import cn.codenst.authservice.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/12 10:36
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/auth/oauth/token")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        //todo 一旦使用@EnableResourceServer，这里的http配置会将WebSecurityConfigurer中http的配置覆盖掉
        http.authorizeRequests()
                .antMatchers("/login","/auth/oauth/token", "/authentication/form").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}
