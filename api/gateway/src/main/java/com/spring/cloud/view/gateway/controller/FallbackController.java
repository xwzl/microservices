package com.spring.cloud.view.gateway.controller;

import com.spring.cloud.common.until.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断回调接口
 *
 * @author xuweizhi
 * @since 2020/07/05 21:27
 */
@RestController
public class FallbackController {

    @GetMapping("fallback")
    public ApiResult<String> fallback() {
        return new ApiResult<>("默认降级逻辑");
    }
}
