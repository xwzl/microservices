package com.sping.cloud.view.nacos.controller;

import com.spring.cloud.common.module.feign.NacosUmService;
import com.spring.cloud.common.until.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URISyntaxException;

/**
 * 负载均衡测试
 *
 * @author xuweizhi
 * @since 2020/07/02 13:14
 */
@Api
@Slf4j
@RestController
@RequestMapping("rest")
public class LoadBalanceController {

    @Resource
    private NacosUmService nacosUmService;

    @GetMapping("discovery")
    @ApiOperation("discovery")
    public DiscoveryClient discoveryInfo() {
        return nacosUmService.discoveryInfo();
    }

    @GetMapping("restBalance")
    public ApiResult restBalance() throws URISyntaxException {
        return nacosUmService.restBalance();
    }
}
