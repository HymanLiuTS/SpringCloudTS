package cn.codenst.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/28 10:18
 * @description：
 * @modified By：
 * @version: $
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;



    /*
     * 配置授权服务器的安全，意味着/oauth/token端点和/oauth/authorize端点都应该是安全的
     * 默认的设置副高了绝大多数的需求，所以一般情况下不需要做任何事情
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient("clientId")
                .secret(new BCryptPasswordEncoder().encode("clientSecret"))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all");
    }

    /*
     * 该方法是用来配置授权服务器特性的（Authorization Server endpoints ）,主要是一些非安全的特性，比如
     * token存储、token的定义、授权模式等等
     * 默认不需要任何配置，如果需要密码授权，则需要提供一个AuthenticationManager
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //todo 使用jwtTokenEnhancer可以自定义拓展字段
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));

        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        //todo 这里可以设置jdbcTokenStore，将产生的token放到数据库中，默认存到内存中;管理token存储位置的抽象接口是
        //TokenStore，具体实现类有 InMemoryTokenStore、JdbcTokenStore、JwtTokenStore 和RedisTokenStore\        //endpoints.tokenStore(jdbcTokenStore())
    }


}
