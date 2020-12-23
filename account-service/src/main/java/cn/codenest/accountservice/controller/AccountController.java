package cn.codenest.accountservice.controller;

import cn.codenest.accountservice.config.ServiceConfig;
import cn.codenest.accountservice.filter.UserContextHolder;
import cn.codenest.accountservice.po.License;
import cn.codenest.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping(value = "/v1/account/")
public class AccountController {

    @Autowired
    ServiceConfig config;


    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/{accountid}/phone", method = RequestMethod.GET)
    public String getPhone(@PathVariable String accountid) {
        return config.getPhone();
    }

    @RequestMapping(value = "/{accountid}/license", method = RequestMethod.GET)
    public License getLicense(@PathVariable String accountid) {
        //return accountService.getLicenseByDiscoveryClient();
        //return accountService.getLicenseByRibbonAndDiscoveryClient();
        System.out.println("AccountController:" + UserContextHolder.getContext().getCorrelationId());
        return accountService.getLicense();
    }
}
