package cn.codenest.licenseservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 11:20
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@Slf4j
public class UserContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        System.out.println("UserContextFilter:" + UserContextHolder.getContext().getCorrelationId());
        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
