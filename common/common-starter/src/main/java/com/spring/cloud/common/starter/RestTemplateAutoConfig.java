package com.spring.cloud.common.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
public class RestTemplateAutoConfig {

    /**
     * 服务调用方就是进行负载均衡的一方，利用 ribbon 的 RestTemplate 进行负载调用服务。
     */
    @Bean
    @LoadBalanced
    @ConditionalOnClass(name = {"org.springframework.web.client.RestTemplate"})
    @ConditionalOnProperty(prefix = "ribbon", name = "balance", havingValue = "true")
    public RestTemplate restTemplate() {
        log.info("Current system application load balance restTemplate bean .............");
        return new RestTemplate();
    }
}
