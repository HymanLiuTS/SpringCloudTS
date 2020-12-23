package cn.codenest.accountservice.client;

import cn.codenest.accountservice.po.License;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h3>SpringCloudTS</h3>
 * <p></p>
 *
 * @author : Hyman
 * @date : 2020-12-22 17:49
 **/
@FeignClient(value = "license-service", url = "")
@RequestMapping(value = "/v1/organizations/")
public interface AccountFeignClient {


    @RequestMapping(value = "/{licenseid}/licenses",method = RequestMethod.GET)
    //断路器不能用到feign上
    //@HystrixCommand
    License getLicense(@PathVariable String licenseid);
}
