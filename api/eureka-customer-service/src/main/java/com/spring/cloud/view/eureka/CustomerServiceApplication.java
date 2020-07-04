package com.spring.cloud.view.eureka;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户管理模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:03
 */
@EnableSwagger2
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
@ComponentScan(basePackages = {"com.spring.cloud.common.module.feign","com.spring.cloud.view.eureka"})
@EnableFeignClients(basePackages = "com.spring.cloud.common.module.feign")
public class CustomerServiceApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
