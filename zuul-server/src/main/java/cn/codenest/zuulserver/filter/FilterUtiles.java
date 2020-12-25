package cn.codenest.zuulserver.filter;

import com.netflix.zuul.context.RequestContext;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/25 8:55
 * @description：
 * @modified By：
 * @version: $
 */
public class FilterUtiles {
    public final static String PRE_FILTER_TYPE = "pre";
    public final static String CORRELATION_ID = "correlation_id";

    public static String getRelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(CORRELATION_ID) != null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }
        return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
    }

    public static void setRelationId(String relationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, relationId);
    }
}
