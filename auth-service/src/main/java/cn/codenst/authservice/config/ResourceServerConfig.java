package cn.codenst.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/12 10:36
 * @description：
 * @modified By：
 * @version: $
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "authorizationserver";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //只有/me端点作为资源服务器的资源,否则oauth会把所有的端点当成oauth资源服务资源
        http.requestMatchers().antMatchers("/user/me")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

}
