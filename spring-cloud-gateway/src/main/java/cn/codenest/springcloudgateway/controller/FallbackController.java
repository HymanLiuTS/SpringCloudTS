package cn.codenest.springcloudgateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hyman
 * @date ：Created in 2021/1/25 17:44
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @RequestMapping("")
    public ResponseEntity fallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("服务暂不可用！！！！");
    }
}
