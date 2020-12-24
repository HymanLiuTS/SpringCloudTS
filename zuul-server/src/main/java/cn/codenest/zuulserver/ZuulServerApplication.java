package cn.codenest.zuulserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulServerApplication {

    @Value("${license.autocontrol}")
    private String autoLicense;

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}
