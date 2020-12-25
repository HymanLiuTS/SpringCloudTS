package cn.codenest.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/25 8:52
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class RespondFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Override
    public String filterType() {
        //告诉zuul，改过滤器是前置、后置还是路由的
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //指示不同类型的过滤器的执行顺序
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        //指示改过滤器是否执行
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        if (FilterUtiles.getRelationId() != null) {
            return true;
        }
        return false;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println("处理完成响应:" + FilterUtiles.getRelationId());
        ctx.getResponse().addHeader(FilterUtiles.CORRELATION_ID,FilterUtiles.getRelationId());
        return null;
    }
}
