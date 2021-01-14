package cn.codenest.accountservice.client;

import cn.codenest.accountservice.po.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/22 13:43
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class LicenseDistoryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public License getLicenseByDiscoveryClient() {
        //这里new了一个RestTemplate，而不是用注入的方式，是因为使用了@EnableDiscoveryClient
        //注解之后，由spring框架管理的RestTemplate都将注入一个启用了ribbon的拦截器，将会改变构建url的行为
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> is = discoveryClient.getInstances("license-service");
        if (is.size() == 0) {
            return null;
        }
        String serviceUri = String.format("%s/v1/organizations/%s/licenses",
                is.get(0).getUri().toString(),
                "autocontrol");
        ResponseEntity<License> restExchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                License.class,
                "license-service"
        );
        return restExchange.getBody();
    }

    public License getLicenseByRibbonAndDiscoveryClient() {
        String serviceUri = String.format("http://license-service/v1/organizations/%s/licenses",
                "autocontrol");
        ResponseEntity<License> restExchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                License.class,
                "license-service"
        );
        return restExchange.getBody();
    }
}
