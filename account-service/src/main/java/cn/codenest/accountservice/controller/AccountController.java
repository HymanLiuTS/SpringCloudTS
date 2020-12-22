package cn.codenest.accountservice.controller;

import cn.codenest.accountservice.client.AccountFeignClient;
import cn.codenest.accountservice.client.LicenseDistoryClient;
import cn.codenest.accountservice.config.ServiceConfig;
import cn.codenest.accountservice.po.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/18 15:56
 * @description：
 * @modified By：
 * @version: $
 */

@RestController
@RequestMapping(value = "/v1/account/")
public class AccountController {

    @Autowired
    ServiceConfig config;

    @Autowired
    LicenseDistoryClient client;

    @Autowired
    AccountFeignClient accountFeignClient;

    @RequestMapping(value = "/{accountid}/phone", method = RequestMethod.GET)
    public String getPhone(@PathVariable String accountid) {
        return config.getPhone();
    }

    @RequestMapping(value = "/{accountid}/license", method = RequestMethod.GET)
    public License getLicense(@PathVariable String accountid) {
        //return client.getLicenseByDiscoveryClient();
        //return client.getLicenseByRibbonAndDiscoveryClient();
        return accountFeignClient.getLicense("autocontrol");
    }
}
