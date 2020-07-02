package com.sping.cloud.view.eureka.controller;

import com.spring.cloud.common.module.feign.EurekaUmService;
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
@Slf4j
@RestController
@RequestMapping("rest")
public class LoadBalanceController {

    @Resource
    private EurekaUmService eurekaUmService;

    @GetMapping("discovery")
    public DiscoveryClient discoveryInfo() {
        return eurekaUmService.discoveryInfo();
    }
}
