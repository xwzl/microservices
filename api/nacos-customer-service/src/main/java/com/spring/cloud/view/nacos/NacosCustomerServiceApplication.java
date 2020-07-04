package com.spring.cloud.view.nacos;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户管理模块
 * <p>
 * 改变 ribbon 负载均衡策略，个人认为这种方式很蠢。经测试，IRule 只要被注入到 IoC 容器中后，
 * ribbon 默认的轮询算法就已经失效，将采用你注入的轮询算法。因此直接修改配置文件方式来达到替
 * 负载均衡的实现类.ribbon.strategy: random
 * <p>
 * OpenFeign 的使用 @EnableFeignClients
 *
 * @author xuweizhi
 * @since 2020/07/01 23:03
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.spring.cloud.common.module.feign")
//@RibbonClient(name = "NACOS-UM", configuration = RibbonRuleAutoConfig.class)
public class NacosCustomerServiceApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(NacosCustomerServiceApplication.class, args);
    }
}
