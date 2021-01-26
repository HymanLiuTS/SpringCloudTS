package cn.codenest.licenseservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/21 10:26
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@RefreshScope
public class ServiceConfig {

    @Value("${license.autocontrol}")
    private String autoLicense;

    @Value("${license.mannulcontrol}")
    private String mannulLicense;

    public String getAutoLicense() {
        return autoLicense;
    }

    public String getMannulLicense() {
        return mannulLicense;
    }

}
