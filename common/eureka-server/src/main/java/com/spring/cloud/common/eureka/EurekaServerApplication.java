package com.spring.cloud.common.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server
 * <p>
 * http://localhost:8000/  控制台
 * http://localhost:8000/eureka/ 服务注册地址
 *
 * @author xuweizhi
 * @since 2020/07/01 22:48
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
