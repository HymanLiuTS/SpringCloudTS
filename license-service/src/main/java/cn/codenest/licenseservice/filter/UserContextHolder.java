package cn.codenest.licenseservice.filter;

import org.springframework.util.Assert;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 11:13
 * @description：
 * @modified By：
 * @version: $
 */
public class UserContextHolder {
    private static ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();
        if (context == null) {
            context = new UserContext();
            userContext.set(context);
        }
        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "context");
        userContext.set(context);
    }
}
