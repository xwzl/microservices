package com.spring.cloud.nacos.um;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户管理模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:03
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.spring.cloud.common.module.feign")
public class NacosServiceApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(NacosServiceApplication.class, args);
    }
}
