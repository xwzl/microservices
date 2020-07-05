package com.spring.cloud.view.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 路由配置
 *
 * @author xuweizhi
 * @since 2020/07/05 18:48
 */
@Configuration
public class GatewayConfig {

    /**
     * 获取请求用户ip作为限流key。
     */
    @Bean
    public KeyResolver hostAddrKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }

    //获取请求用户id作为限流key。
    //@Bean
    //public KeyResolver userKeyResolver() {
    //    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    //}

    //获取请求地址的uri作为限流key。
    //@Bean
    //KeyResolver apiKeyResolver() {
    //    return exchange -> Mono.just(exchange.getRequest().getPath().value());
    //}

    /**
     * 感觉这样写没卵用
     */
    @Bean
    public RouteLocator eurekaApiRouteLocator(RouteLocatorBuilder routeBuilder) {
        return routeBuilder.routes()
                //.route("hello", desc -> desc.path("/**").uri("http://localhost:8101/**"))
                //.route("hello", desc -> desc.path("/**").uri("lb://eureka-customer/**"))
                // 接口稀少，节约测试 uri
                .route("hello", desc -> desc.path("/hystrix/randomException").uri("http://localhost:8101/hystrix/randomException"))
                .build();
    }
}
