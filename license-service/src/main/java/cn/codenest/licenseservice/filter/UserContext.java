package cn.codenest.licenseservice.filter;

import lombok.Data;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 11:14
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class UserContext {
    public static String CORRELATION_ID = "correlation_id";
    public static String TOKEN = "token";
    private String CorrelationId;
}
