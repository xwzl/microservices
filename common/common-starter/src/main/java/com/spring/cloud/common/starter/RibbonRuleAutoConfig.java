package com.spring.cloud.common.starter;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon 负载均衡策略自动配置
 *
 * @author xuweizhi
 * @since 2020/07/03 15:28
 */
@Slf4j
@Configuration
public class RibbonRuleAutoConfig {

    /**
     * 在 SpringBootApplication 同级或者包路径下，当前服务的所有 ribbon 负载均衡策略都会被覆盖。
     * <p>
     * {@link IRule} 其实类 RoundRobinRule 轮询算法，是根据请求数与被调用服务总数取余,来获取 list 中的
     * 服务达到轮询的目的。每次有新服务加入时，请求数重新开始计算。
     * <p>
     * 文档：2、Ribbon 负载均衡的使用.md
     * 链接：http://note.youdao.com/noteshare?id=fb9e8795ded1c2dcbe12699fae08df6d&sub=42A74138A16D454FACAC27A3CA126D56
     */
    @Bean
    @ConditionalOnProperty(prefix = "ribbon", name = "strategy", havingValue = "random")
    public IRule myRandomRule() {
        log.info("custom ribbon strategy init with random load balance ......");
        return new RandomRule();
    }
}
