package cn.codenest.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//这个注解在使用feign调用其他服务时用，使用集成ribbon的DiscoveryClient和DiscoveryClient无需注解
@EnableFeignClients
//这个注解在使用DiscoveryClient直接调用其他服务时用，使用集成ribbon和feign无需注解
@EnableDiscoveryClient
//启动断路器hystrix
@EnableCircuitBreaker
@SpringBootApplication
public class AccountServiceApplication {

    //这个注解在使用集成ribbon的resttenplate调用服务时用，在DiscoveryClient直接调用其他服务时用和feign无需注解
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
