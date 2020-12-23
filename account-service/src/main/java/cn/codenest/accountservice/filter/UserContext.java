package cn.codenest.accountservice.filter;

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
    public static String CORRELATION_ID = "CORRELATION_ID";
    private String CorrelationId;
}
