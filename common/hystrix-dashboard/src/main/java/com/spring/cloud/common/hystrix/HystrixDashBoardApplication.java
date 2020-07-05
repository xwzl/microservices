package com.spring.cloud.common.hystrix;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * http://localhost:8004/hystrix
 * <p>
 * http://localhost:8201/actuator/hystrix.stream
 *
 * @author xuweizhi
 * @since 2020/07/01 22:48
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashBoardApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(HystrixDashBoardApplication.class, args);
    }
}
