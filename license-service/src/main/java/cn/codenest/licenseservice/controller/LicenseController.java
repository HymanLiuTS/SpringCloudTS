package cn.codenest.licenseservice.controller;

import brave.Span;

import brave.Tracer;
import cn.codenest.licenseservice.component.SimpleSourceBean;
import cn.codenest.licenseservice.config.ServiceConfig;
import cn.codenest.licenseservice.po.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @Autowired
    ServiceConfig config;

    @Autowired
    private Tracer tracer;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    @RequestMapping(value = "/{licenseid}/licenses", method = RequestMethod.GET)
    public License getLicense(@PathVariable String licenseid) {
        System.out.println("getLicense");
        License license = new License();
        try {
            if (licenseid.equals("autocontrol")) {
                license.setId(licenseid);
                license.setLicenseType("Teleco");
                license.setVal(config.getAutoLicense());
            } else {
                license.setId(licenseid);
                license.setLicenseType("Teleco");
                license.setVal(config.getMannulLicense());
            }
            simpleSourceBean.publishOrgChange("Save", UUID.randomUUID().toString());
        } finally {

        }
        return license;
    }

    @RequestMapping(value = "/{licenseid}/licenses", method = RequestMethod.DELETE)
    public License delLicense(@PathVariable String licenseid) {
        System.out.println("getLicense");
        License license = new License();
        if (licenseid.equals("autocontrol")) {
            license.setId(licenseid);
            license.setLicenseType("Teleco");
            license.setVal(config.getAutoLicense());
        } else {
            license.setId(licenseid);
            license.setLicenseType("Teleco");
            license.setVal(config.getMannulLicense());
        }

        return license;
    }
}
