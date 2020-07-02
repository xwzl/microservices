package com.spring.cloud.common.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 配置
 *
 * @author xuweizhi
 * @since 2020/07/02 15:52
 */
@Slf4j
@Configuration
@ConditionalOnClass(name = {"org.springframework.web.client.RestTemplate"})
public class RestAutoConfig {

    /**
     * LoadBalanced 负载均衡注解
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        log.info("common start init rest load balance template ......");
        return new RestTemplate();
    }
}
