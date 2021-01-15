package cn.codenest.accountservice.listener;

import cn.codenest.accountservice.client.HelloBinding;
import cn.codenest.accountservice.domain.OrgChangeModel;
import cn.hutool.json.JSONUtil;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/15 11:10
 * @description：
 * @modified By：
 * @version: $
 */
//生成HelloBinding代理对象
@EnableBinding(HelloBinding.class)
public class HelloListener {
    @StreamListener(target = HelloBinding.GREETING)
    public void processHelloChannelGreeting(
            OrgChangeModel or,
             @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag
            /*,@Header(AmqpHeaders.CHANNEL) Channel channel//这里获取通道报错*/
    ) throws IOException {
        System.out.println(JSONUtil.toJsonStr(or));
        //手动确认消息
        //channel.basicAck(deliveryTag, false);
    }
}
