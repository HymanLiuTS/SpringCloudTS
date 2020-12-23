package cn.codenest.accountservice.service.impl;

import cn.codenest.accountservice.client.AccountFeignClient;
import cn.codenest.accountservice.client.LicenseDistoryClient;
import cn.codenest.accountservice.config.ServiceConfig;
import cn.codenest.accountservice.po.License;
import cn.codenest.accountservice.service.AccountService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 9:21
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    ServiceConfig config;

    @Autowired
    LicenseDistoryClient client;

    @Autowired
    AccountFeignClient accountFeignClient;

    @Override
    public License getLicenseByDiscoveryClient() {
        return client.getLicenseByDiscoveryClient();
    }

    @Override
    public License getLicenseByRibbonAndDiscoveryClient() {
        return client.getLicenseByRibbonAndDiscoveryClient();
    }

    //断路器用到服务类方法上
    @HystrixCommand(
            //设置超时,默认是1000毫秒
            commandProperties = {@HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "1200"
            )},
            //启用后备模式，设置服务失败时调用的替代方法
            fallbackMethod = "getFallbackLicense",
            //启动舱壁
            threadPoolKey = "licenseThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(
                            name = "coreSize",
                            value = "30"),
                    @HystrixProperty(
                            name = "maxQueueSize",
                            value = "10")
            }
    )
    @Override
    public License getLicense() {
        return accountFeignClient.getLicense("autocontrol");
    }

    public License getFallbackLicense() {
        License license = new License();
        license.setId("0");
        license.setLicenseType("虚拟服务类型");
        license.setProductName("虚拟服务");
        license.setVal("0");
        return license;
    }
}
