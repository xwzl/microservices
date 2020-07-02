package com.sping.cloud.view.eureka;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户管理模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:03
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.spring.cloud.common.module.feign")
public class CustomerServiceApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
