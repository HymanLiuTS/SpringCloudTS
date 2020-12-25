package cn.codenest.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/25 13:42
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class RoutesFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    //请求超时时间,这个时间定义了socket读数据的超时时间，也就是连接到服务器之后到从服务器获取响应数据需要等待的时间,发生超时，会抛出SocketTimeoutException异常。
    private static final int SOCKET_TIME_OUT = 60000;
    //连接超时时间,这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间。发生超时，会抛出ConnectionTimeoutException异常
    private static final int CONNECT_TIME_OUT = 60000;

    @Override
    public String filterType() {
        //告诉zuul，改过滤器是前置、后置还是路由的
        return FilterConstants.ROUTE_TYPE;
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

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        AbTestingRoute abTestingRoute = getAbTestingRouteInfo(FilterUtiles.getServiceId());
        StringBuffer sb = ctx.getRequest().getRequestURL();
        if (abTestingRoute != null && useSpecialRoute(abTestingRoute)) {
            RibbonFilterContextHolder.getCurrentContext()
                    .add("lancher", "1");

        } else {
            RibbonFilterContextHolder.getCurrentContext()
                    .add("lancher", "2");

        }
        return null;
    }

    private boolean useSpecialRoute(AbTestingRoute abTestingRoute) {
        Random random = new Random();
        if (abTestingRoute.getActive().equals("N")) {
            return false;
        }
        int value = random.nextInt((10 - 1) + 1) + 1;
        if (abTestingRoute.getWeight() < value) {
            return true;
        }
        return false;
    }

    private AbTestingRoute getAbTestingRouteInfo(String serviceName) {
        AbTestingRoute abTestingRoute = new AbTestingRoute();
        abTestingRoute.setActive("Y");
        abTestingRoute.setWeight(5);
        return abTestingRoute;
    }
}
