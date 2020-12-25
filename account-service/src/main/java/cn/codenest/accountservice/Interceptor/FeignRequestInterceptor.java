package cn.codenest.accountservice.Interceptor;

import cn.codenest.accountservice.filter.UserContext;
import cn.codenest.accountservice.filter.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/25 11:30
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header(UserContext.CORRELATION_ID,UserContextHolder.getContext().getCorrelationId());
        template.header(UserContext.TOKEN,"mytoken");
    }
}
