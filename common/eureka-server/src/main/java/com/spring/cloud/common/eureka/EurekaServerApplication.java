package com.spring.cloud.common.eureka;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server
 * <p>
 * http://localhost:8000/  控制台
 * http://localhost:8000/eureka/ 服务注册地址
 *
 *
 * server 启动需要把 根目录下 config/bootstrap.yml 文件中 spring.profiles.active 注释掉
 *
 * @author xuweizhi
 * @since 2020/07/01 22:48
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
