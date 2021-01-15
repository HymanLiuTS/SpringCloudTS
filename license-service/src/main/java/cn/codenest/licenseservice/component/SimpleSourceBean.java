package cn.codenest.licenseservice.component;

import cn.codenest.licenseservice.domain.OrgChangeModel;
import cn.codenest.licenseservice.filter.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/15 9:32
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@Slf4j
public class SimpleSourceBean {

    private MessageChannel messageChannel;

    @Autowired
    public SimpleSourceBean(@Autowired HelloBinding binding) {
        this.messageChannel = binding.greeting();
    }

    public void publishOrgChange(String action, String orgId) {
        log.info("sending rbmq message {} for orgid:{}", action, orgId);
        OrgChangeModel changeModel = new OrgChangeModel();
        changeModel.setAction(action);
        changeModel.setOrgId(orgId);
        changeModel.setTypeName(OrgChangeModel.class.getTypeName());
        changeModel.setRelationId(UserContext.CORRELATION_ID);
        messageChannel.send(MessageBuilder.withPayload(changeModel).build());
    }

}
