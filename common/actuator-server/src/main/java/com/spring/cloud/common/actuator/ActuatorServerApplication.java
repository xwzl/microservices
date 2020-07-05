package com.spring.cloud.common.actuator;

import com.spring.cloud.common.until.warning.ReflectionUtil;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuweizhi
 * @since 2020/07/01 22:48
 */
@EnableAdminServer
@SpringBootApplication
public class ActuatorServerApplication {

    public static void main(String[] args) {
        ReflectionUtil.disableAccessWarnings();
        SpringApplication.run(ActuatorServerApplication.class, args);
    }
}
