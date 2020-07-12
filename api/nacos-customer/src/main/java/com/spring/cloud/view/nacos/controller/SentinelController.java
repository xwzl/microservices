package com.spring.cloud.view.nacos.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.common.starter.StarterAutoConfig;
import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.common.until.exception.ServiceException;
import com.spring.cloud.view.nacos.config.CustomerHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sentinel
 *
 * @author xuweizhi
 * @since 2020/07/09 22:12
 */
@Slf4j
@Api
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    /**
     * 匀速等待
     */
    @GetMapping("lineUp")
    public String lineUp() throws InterruptedException {
        Thread.sleep(500);
        log.info("当请求超过阈值，按固定速率处理请求，只能 qps 的情况");
        return "";
    }

    /**
     * 匀速等待:验证异常关闭全局处理异常
     * <p>
     * {@link StarterAutoConfig}
     */
    @GetMapping("exception")
    public String exception() {
        int i = 10 / 0;
        return "";
    }

    /**
     * 违背热点规则将进行降级： downgradeMethod 只处理 blockException 即各种配置产生的异常。
     */
    @GetMapping("testHotKey")
    @ApiOperation("testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "downgradeMethod")
    public String testHotKey(String name, String address) {
        int i = 10 / 0;
        return name;
    }

    public String downgradeMethod(String name, String address, BlockException blockException) {
        return "降级方法";
    }

    /**
     * 类似于 provider 的用法，方法必须是静态方法,
     * <p>
     * 必须对资源进行限流，否则不成功；假如服务某段时间内出现异常，调用 CustomerHandler 类的 customerHandler 方法。
     * <p>
     * https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81
     */
    @GetMapping("customerHandler")
    @SentinelResource(value = "customerHandler", blockHandlerClass = CustomerHandler.class, blockHandler = "customerHandlers")
    public ApiResult<String> customerHandler() {
        return new ApiResult<>("正常返回");
    }

    /**
     * 熔断：如果 fallback 和 blockHandler 都配置，则处理时违背了控制台的配置，则全部被 blockHandler 处理，
     * 否则由 fallback 处理
     */
    @GetMapping("fallback")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")// fallback 只处理业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler")// blockHandler 只负责 sentinel 控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")// blockHandler 只负责 sentinel 控制台配置违规
    public ApiResult<String> fallback(String id) {
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
