package com.spring.cloud.view.eureka.controller;

import com.spring.cloud.common.until.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
