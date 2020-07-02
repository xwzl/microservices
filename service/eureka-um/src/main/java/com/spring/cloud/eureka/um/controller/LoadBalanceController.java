package com.spring.cloud.eureka.um.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private Integer port;

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("discovery")
    public DiscoveryClient discoveryInfo() {
        log.info("Current service's port is {}", port);
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("Register service contain {}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(name.toUpperCase());
        for (ServiceInstance instance : instances) {
            log.info("Current service contain {},ip {},port{},uri {}", instance.getServiceId(),
                    instance.getHost(), instance.getPort(), instance.getUri());
        }
        return null;
    }
}
