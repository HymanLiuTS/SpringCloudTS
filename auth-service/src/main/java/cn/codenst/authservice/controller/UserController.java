package cn.codenst.authservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/12 10:03
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
public class UserController {

    @GetMapping("/resource/phone")
    public String phone() {
        return "12345678901";
    }

    @GetMapping("/public/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/admin/num")
    public String num() {
        return "9573";
    }

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/user/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String me() throws JsonProcessingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        return objectMapper.writeValueAsString(principal);
    }

}
