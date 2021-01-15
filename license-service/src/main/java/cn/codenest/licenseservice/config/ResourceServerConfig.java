package cn.codenest.licenseservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/12 18:03
 * @description：
 * @modified By：
 * @version: $
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //允许多请求地址多加斜杠  比如 /msg/list   //msg/list
    //这个bean的作用是：防止通过服务调用本服务的接口时出现：
    // org.springframework.security.web.firewall.RequestRejectedException:
    // The request was rejected because the URL was not normalized.
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/v1/organizations/*")
                .hasRole("ADMIN")
                .anyRequest()
                .permitAll();
    }

}
