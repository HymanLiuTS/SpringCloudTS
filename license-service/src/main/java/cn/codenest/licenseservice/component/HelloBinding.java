package cn.codenest.licenseservice.component;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <h3>SpringCloudTS</h3>
 * <p></p>
 *
 * @author : Hyman
 * @date : 2021-01-15 10:33
 **/
public interface HelloBinding {
    @Output("greetingChannel")
    MessageChannel greeting();
}
