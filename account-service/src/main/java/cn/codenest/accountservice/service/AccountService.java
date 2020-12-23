package cn.codenest.accountservice.service;

import cn.codenest.accountservice.po.License;

/**
 * <h3>SpringCloudTS</h3>
 * <p></p>
 *
 * @author : Hyman
 * @date : 2020-12-23 09:19
 **/
public interface AccountService {
    public License getLicenseByDiscoveryClient();
    public License getLicenseByRibbonAndDiscoveryClient();
    public License getLicense();
}
