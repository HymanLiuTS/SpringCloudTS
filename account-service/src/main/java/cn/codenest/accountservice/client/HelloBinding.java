package cn.codenest.accountservice.client;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <h3>SpringCloudTS</h3>
 * <p></p>
 *
 * @author : HYman
 * @date : 2021-01-15 11:09
 **/
public interface HelloBinding {
    String GREETING = "greetingExchange";

    @Input(GREETING)
    SubscribableChannel greeting();
}
