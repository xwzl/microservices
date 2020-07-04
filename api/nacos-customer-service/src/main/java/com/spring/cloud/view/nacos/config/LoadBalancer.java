package com.spring.cloud.view.nacos.config;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡接口
 *
 * @author xuweizhi
 * @since 2020/07/03 16:34
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
