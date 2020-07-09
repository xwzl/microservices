package com.spring.cloud.view.nacos.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.common.starter.StarterAutoConfig;
import lombok.extern.slf4j.Slf4j;
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
@RestController
@RequestMapping("sentinel")
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
        //int i = 10 / 0;
        return "";
    }

    /**
     * 违背热点规则将进行降级： downgradeMethod 只处理 blockException 即各种配置产生的异常。
     */
    @GetMapping("testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "downgradeMethod")
    public String testHotKey(String name, String address) {
        return name;
    }

    public String downgradeMethod(String name, String address, BlockException blockException) {
        return "降级方法";
    }
}
