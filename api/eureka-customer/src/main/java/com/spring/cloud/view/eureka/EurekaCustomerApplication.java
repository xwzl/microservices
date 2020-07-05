package com.spring.cloud.view.eureka;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户管理模块
 * http://localhost:8004/hystix 服务注册地址
 *
 * @author xuweizhi
 * @since 2020/07/01 23:03
 */
@EnableSwagger2
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.spring.cloud.common.module.feign")
@ComponentScan(basePackages = {"com.spring.cloud.common.module.feign", "com.spring.cloud.view.eureka"})
public class EurekaCustomerApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(EurekaCustomerApplication.class, args);
    }

    // 建议用 http://localhost:8201/actuator/hystrix.stream
    //@Bean
    //public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
    //    ServletRegistrationBean<HystrixMetricsStreamServlet> register = new ServletRegistrationBean<>(new HystrixMetricsStreamServlet());
    //    register.setLoadOnStartup(1);
    //    register.setName("hystrixMetricsStreamServlet");
    //    register.addUrlMappings("/hystrix.stream");
    //    return register;
    //}
}
