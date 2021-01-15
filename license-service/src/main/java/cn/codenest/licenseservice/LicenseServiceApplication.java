package cn.codenest.licenseservice;

import cn.codenest.licenseservice.component.HelloBinding;
import cn.codenest.licenseservice.filter.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import javax.servlet.Filter;

@EnableDiscoveryClient
@SpringBootApplication
//EnableBinding注解告诉spring cloud stream将应用程序绑定到消息代理,会产生一个Source消息代理实例
@EnableBinding(HelloBinding.class)
public class LicenseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicenseServiceApplication.class, args);
    }

}
