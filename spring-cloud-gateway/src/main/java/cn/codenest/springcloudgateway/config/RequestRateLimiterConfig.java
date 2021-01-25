package cn.codenest.springcloudgateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/25 17:17
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
public class RequestRateLimiterConfig {

    //使用IP地址为key进行限流,限制每个ip地址访问并发数
    @Primary
    @Bean
    public KeyResolver hostAddrKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    //使用用户id为key进行限流,限制每个用户访问并发数
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    //使用uri为key进行限流,限制每个uri访问的并发数
    @Bean
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

}
