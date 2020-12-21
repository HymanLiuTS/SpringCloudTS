package cn.codenest.licenseservice.po;

import lombok.Data;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/18 15:49
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class License {
    String id;
    String productName;
    String licenseType;
    String val;
}
