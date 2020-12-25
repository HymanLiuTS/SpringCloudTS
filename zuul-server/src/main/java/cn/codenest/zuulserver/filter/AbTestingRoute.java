package cn.codenest.zuulserver.filter;

import lombok.Data;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/25 13:51
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class AbTestingRoute {
    private String active;
    private Integer weight;
}
