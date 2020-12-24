package cn.codenest.zuulserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/24 19:06
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class TestService {

    @Value("${license.autocontrol}")
    private String autoLicense;

}
