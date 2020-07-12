package com.spring.cloud.view.nacos.controller;

import com.spring.cloud.common.module.feign.NacosUmService;
import com.spring.cloud.common.until.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 熔断：
 * <p>
 * 注解 @DefaultProperties 对熔断器没有影响，@HystrixCommand 客户端书写造成熔断器失效。
 *
 * @author xuweizhi
 * @since 2020/07/04 15:51
 */
@Slf4j
@RestController
@RequestMapping("hystrix")
public class HystrixController {

    @Resource
    private NacosUmService nacosUmService;

    /**
     * 测试熔断需要把全局异常处理关闭
     */
    @GetMapping("fallback")
    ApiResult<String> fallback(@RequestParam("id") String id){
        return nacosUmService.fallback(id);
    }

}
