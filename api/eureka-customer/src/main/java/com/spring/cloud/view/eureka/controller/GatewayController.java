package com.spring.cloud.view.eureka.controller;

import com.spring.cloud.common.until.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 网关调用
 *
 * @author xuweizhi
 * @since 2020/07/05 18:01
 */
@Slf4j
@RestController
@RequestMapping("gateway")
public class GatewayController {

    @GetMapping("route")
    public ApiResult<String> route(@RequestParam("path") String path) {
        log.info("Current http request's path {}", path);
        return new ApiResult<>(path);
    }

    @PostMapping("post")
    public ApiResult<String> post() {
        return new ApiResult<>("" + System.currentTimeMillis());
    }

    @GetMapping("hystrix")
    public ApiResult<String> hystrix() {
        if(Math.random() > 0.5){
            throw new RuntimeException();
        }
        return new ApiResult<>("" + System.currentTimeMillis());
    }
}
