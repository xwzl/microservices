package com.spring.cloud.nacos.um.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.common.until.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断测试
 *
 * @author xuweizhi
 * @since 2020/07/04 15:51
 */
@Slf4j
@RestController
@RequestMapping("sentinel")
public class SentinelController {

    @GetMapping("fallback")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    public ApiResult<String> fallback(@RequestParam("id") String id) {
        if (id.equals("4")) {
            throw new ServiceException(-1, "错误");
        }
        return new ApiResult<>(id);
    }

    public ApiResult<String> handlerFallback(String id, Throwable t) {
        log.info(ExceptionUtils.getStackTrace(t));
        return new ApiResult<>("fallback 只处理业务异常");
    }

    public ApiResult<String> blockHandler(String id, BlockException blockException) {
        return new ApiResult<>("blockHandler 只负责 sentinel 控制台配置违规");
    }
}
