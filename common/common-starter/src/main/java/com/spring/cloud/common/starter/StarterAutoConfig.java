package com.spring.cloud.common.starter;


import com.spring.cloud.common.starter.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@ConditionalOnClass(name = {"org.springframework.web.context.request.RequestContextHolder"})
public class StarterAutoConfig {

    @Bean
    public GlobalExceptionHandler exceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 配置默认的 restTemplate Bean ,允许被其他 bean  覆盖
     */
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        log.info("Current system application default restTemplate bean .............");
        return new RestTemplate();
    }

}
