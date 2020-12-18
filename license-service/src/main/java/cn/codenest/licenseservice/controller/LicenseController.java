package cn.codenest.licenseservice.controller;

import cn.codenest.licenseservice.po.License;
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
@RequestMapping(value = "/v1/organizations/")
public class LicenseController {

    @RequestMapping(value = "/{licenseid}/licenses", method = RequestMethod.GET)
    public License getLicense() {
        License license = new License();
        license.setId(1);
        license.setLicenseType("Teleco");
        license.setLicenseType("Seat");
        license.setOrganizationId("TestOrg");
        return license;
    }
}
