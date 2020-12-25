package cn.codenest.accountservice.Interceptor;

import cn.codenest.accountservice.filter.UserContext;
import cn.codenest.accountservice.filter.UserContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/25 10:11
 * @description：
 * @modified By：
 * @version: $
 */
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers=request.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.TOKEN,"mytoken");
        return execution.execute(request,body);
    }

}
