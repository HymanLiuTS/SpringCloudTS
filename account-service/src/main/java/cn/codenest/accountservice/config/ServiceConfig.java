package cn.codenest.accountservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/21 10:26
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class ServiceConfig {

    @Value("${account.phone}")
    private String phone;


    public String getPhone() {
        return phone;
    }

}
