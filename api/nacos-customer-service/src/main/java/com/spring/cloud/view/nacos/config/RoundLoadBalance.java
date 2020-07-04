package com.spring.cloud.view.nacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询实现
 *
 * @author xuweizhi
 * @since 2020/07/03 16:36
 */
@Slf4j
@Component
public class RoundLoadBalance implements LoadBalancer {

    private final AtomicInteger counter = new AtomicInteger(0);


    public final int getAndIncrement() {
        int current;
        int next;
        // 我日他大爷的,一行代码解决，自选的原因是因为高并发情况下出现值重曾家的问题
        do {
            current = this.counter.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.counter.compareAndSet(current, next));
        log.info("current http request numbers:{}", next);
        return next;
        //return this.counter.getAndIncrement();
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        // 类似于循环链表的思想
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
