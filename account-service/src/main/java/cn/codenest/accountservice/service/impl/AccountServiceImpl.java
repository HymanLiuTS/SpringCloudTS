package cn.codenest.accountservice.service.impl;

import cn.codenest.accountservice.client.AccountFeignClient;
import cn.codenest.accountservice.client.LicenseDistoryClient;
import cn.codenest.accountservice.config.ServiceConfig;
import cn.codenest.accountservice.filter.UserContextHolder;
import cn.codenest.accountservice.po.License;
import cn.codenest.accountservice.service.AccountService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 9:21
 * @description：
 * @modified By：
 * @version: $
 */
//类级别的hystrix设置，可以对整个类中的所有HystrixCommand生效
@DefaultProperties(commandProperties={
        @HystrixProperty(
                name = "execution.isolation.thread.timeoutInMilliseconds",
                value = "1200"
        )
})
@Slf4j
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

    @HystrixCommand
    @Override
    public License getLicenseByRibbonAndDiscoveryClient() {
        return client.getLicenseByRibbonAndDiscoveryClient();
    }

    //断路器用到服务类方法上
    @HystrixCommand(

            commandProperties = {
                    //设置超时,默认是1000毫秒
                    @HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "12000"
                    ),
                    //设置跳闸之前在10秒内达到的最小的调用的数量，这是设置10
                    @HystrixProperty(
                            name = "circuitBreaker.requestVolumeThreshold",
                            value = "10"
                    ),
                    //设置达到上述次数之后，判断接口失败的百分比
                    @HystrixProperty(
                            name = "circuitBreaker.errorThresholdPercentage",
                            value = "75"
                    ),
                    //设置跳闸后，休息多少时间，开始尝试调用检查服务是否恢复
                    @HystrixProperty(
                            name = "circuitBreaker.sleepWindowInMilliseconds",
                            value = "7000"
                    ),
                    //设置上述统计最小调用次数的窗口时间，默认是10秒，这里设置成15秒
                    @HystrixProperty(
                            name = "metrics.rollingStats.timeInMilliseconds",
                            value = "15000"
                    ),
                    //定义上述收集失败信息中统计的次数，必须能整除上述窗口时间，比如这里窗口时间为15000，次数为5，则每3秒统计分析一次数据
                    @HystrixProperty(
                            name = "metrics.rollingStats.numBuckets",
                            value = "5"
                    ),
                    @HystrixProperty(
                            name = "execution.isolation.strategy",
                            value = "THREAD"
                    )
            },
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
                            value = "10"),
            }
    )
    @Override
    public License getLicense() {
        System.out.println("AccountService.getLicense:" + UserContextHolder.getContext().getCorrelationId());
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
