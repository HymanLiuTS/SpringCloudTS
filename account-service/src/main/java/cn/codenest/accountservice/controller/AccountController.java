package cn.codenest.accountservice.controller;

import cn.codenest.accountservice.config.ServiceConfig;
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

    @RequestMapping(value = "/{accountid}/phone", method = RequestMethod.GET)
    public String getPhone(@PathVariable String accountid) {
        return config.getPhone();
    }

    @RequestMapping(value = "/{accountid}/license", method = RequestMethod.GET)
    public String getLicense(@PathVariable String accountid) {
        return "123";
    }
}