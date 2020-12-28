package cn.codenst.authservice.entity;

import lombok.Data;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/28 12:04
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
}
