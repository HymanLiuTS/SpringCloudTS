package cn.codenest.springcloudgateway.dto;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/25 17:48
 * @description：
 * @modified By：
 * @version: $
 */

public class Response {
    Integer code;
    String message;

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
