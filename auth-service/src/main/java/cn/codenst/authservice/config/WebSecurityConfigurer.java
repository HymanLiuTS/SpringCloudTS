package cn.codenst.authservice.config;

import cn.codenst.authservice.handler.MyAuthenticationFailureHandler;
import cn.codenst.authservice.handler.MyAuthenticationSuccessHandler;
import cn.codenst.authservice.service.DomainUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/28 10:28
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth.inMemoryAuthentication()
                .withUser("zhangsan")
                .password("123456")
                .roles("USER");*/
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //todo 一旦使用@EnableResourceServer，ResourceServerConfig的http配置会将WebSecurityConfigurer中http的配置覆盖掉
        http.formLogin()
                .loginPage("/auth/oauth/token")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        http.authorizeRequests()
                .antMatchers("/login", "/auth/oauth/token","/authentication/form").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }


    //不定义没有password grant_type
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean(name = "myUserDetailsService")
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new DomainUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
