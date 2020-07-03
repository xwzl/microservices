package com.sping.cloud.view.consule.controller;

import com.spring.cloud.common.module.feign.ConsulUmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private ConsulUmService consulUmService;

    @GetMapping("discovery")
    @ApiOperation("discovery")
    public DiscoveryClient discoveryInfo() {
        return consulUmService.discoveryInfo();
    }
}
