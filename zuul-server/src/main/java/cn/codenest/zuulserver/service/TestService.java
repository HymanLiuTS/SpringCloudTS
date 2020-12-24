package cn.codenest.zuulserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/24 19:06
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class TestService {

    @Value("${zuultest.autocontrol}")
    private String autoLicense;

}
