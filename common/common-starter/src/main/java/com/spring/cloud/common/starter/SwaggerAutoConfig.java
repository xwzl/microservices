package com.spring.cloud.common.starter;

import com.spring.cloud.common.starter.config.ApplicationRunnerAfter;
import com.spring.cloud.common.starter.config.IpConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author xuweizhi
 * @since 2020/07/02 12:15
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger.print",name = "enable",havingValue = "true")
public class SwaggerAutoConfig {

    @Bean
    public IpConfiguration ipConfiguration() {
        return new IpConfiguration();
    }

    @Bean
    @Order(-100)
    @ConditionalOnClass(name = "springfox.documentation.swagger2.annotations.EnableSwagger2")
    public ApplicationRunnerAfter applicationRunnerAfter() {
        return new ApplicationRunnerAfter();
    }
}
