package cn.codenest.licenseservice.domain;

import lombok.Data;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/15 9:42
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class OrgChangeModel {
    private String typeName;
    private String orgId;
    private String relationId;
    private String action;
}
