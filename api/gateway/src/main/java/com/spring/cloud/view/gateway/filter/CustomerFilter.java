package com.spring.cloud.view.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 网关全局过滤器
 *
 * @author xuweizhi
 * @since 2020/07/05 22:54
 */
@Slf4j
@Component
@Order(-100)
public class CustomerFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("全局过滤器{}", LocalDateTime.now());
        ServerHttpRequest request = exchange.getRequest();
        List<String> token = request.getHeaders().get("token");
        if (CollectionUtils.isEmpty(token)) {
            log.error("请在 headers 中加 token 校验");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
