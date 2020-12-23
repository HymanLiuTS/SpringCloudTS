package cn.codenest.accountservice.hystrix;

import cn.codenest.accountservice.filter.UserContext;
import cn.codenest.accountservice.filter.UserContextHolder;

import java.util.concurrent.Callable;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 12:09
 * @description：
 * @modified By：
 * @version: $
 */
public final class DelegatingUserContextCallable<V> implements Callable<V> {

    private final Callable<V> deletgate;
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> deletgate, UserContext userContext) {
        this.deletgate = deletgate;
        this.originalUserContext = userContext;
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);
        try {
            return deletgate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
