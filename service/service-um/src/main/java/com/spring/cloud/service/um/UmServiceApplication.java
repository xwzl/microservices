package com.spring.cloud.service.um;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户管理模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:03
 */
@EnableEurekaClient
@SpringBootApplication
public class UmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmServiceApplication.class, args);
    }
}
