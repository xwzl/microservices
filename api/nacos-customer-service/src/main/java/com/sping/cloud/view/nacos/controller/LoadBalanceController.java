package com.sping.cloud.view.nacos.controller;

import com.sping.cloud.view.nacos.config.LoadBalancer;
import com.spring.cloud.common.module.feign.NacosUmService;
import com.spring.cloud.common.starter.RestTemplateAutoConfig;
import com.spring.cloud.common.starter.StarterAutoConfig;
import com.spring.cloud.common.until.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 负载均衡测试
 *
 * @author xuweizhi
 * @since 2020/07/02 13:14
 */
@Api
@Slf4j
@RestController
@RequestMapping("rest")
@FeignClient
public class LoadBalanceController {

    @Resource
    private NacosUmService nacosUmService;

    @Resource
    private LoadBalancer roundLoadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * tips:
     * <p>
     * 幸好知识库储备够，不然由 @LoadBalance 注解引起的 restTemplate 对象增强 bean 无法在 loadBalance 成功调用。
     * <p>
     * 在 {@link RestTemplateAutoConfig} 中注册 LoadBalance RestTemplate 客户端，有负载均衡调用的功能，但是这个
     * RestTemplate 在调用 ip:port/sourcePath 路径时会报错。有一种简单粗暴的解决方式，在每次调用 loadBalance 方法
     * 时每次重新 new RestTemplate() 对象，但是这种方式不够优雅。
     * <p>
     * 因此想到了 SpringFramework 中提供的条件注解，@ConditionalOnMissingBean,在当前应用不包含当前 bean 时，会创建
     * 一个默认的 restTemplate,具体实现在 {@link StarterAutoConfig} 中。当 {@link RestTemplateAutoConfig} 配置
     * 中配置 restTemplate 时会覆盖 {@link StarterAutoConfig} 配置的 restTemplate,每次注入时获取的是 LoadBalance
     * RestTemplate Bean.
     */
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("discovery")
    @ApiOperation("discovery")
    public DiscoveryClient discoveryInfo() {
        return nacosUmService.discoveryInfo();
    }

    @GetMapping("restBalance")
    public ApiResult<Integer> restBalance() throws URISyntaxException {
        ApiResult<Integer> balance = nacosUmService.balance();
        log.info("{}", balance.getData());
        return balance;
    }

    @GetMapping("loadBalance")
    public Object loadBalance() {
        List<ServiceInstance> instances = discoveryClient.getInstances("nacos-um");
        if (CollectionUtils.isEmpty(instances)) {
            return new ApiResult<>();
        }
        ServiceInstance instance = roundLoadBalance.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/rest/balance", Object.class);
    }
}
