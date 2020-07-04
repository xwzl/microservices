package com.spring.cloud.view.eureka.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.common.module.feign.EurekaUmService;
import com.spring.cloud.common.until.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 熔断测试
 *
 * @author xuweizhi
 * @since 2020/07/04 15:51
 */
@Slf4j
@RestController
@RequestMapping("hystrix")
public class HystrixController {

    @Resource
    private EurekaUmService eurekaUmService;

    private final static AtomicInteger failCounter = new AtomicInteger(1);
    private final static AtomicInteger successCounter = new AtomicInteger(1);
    private static int FAIL_COUNT = 0;
    private static int SUCCESS_COUNT = 0;

    /**
     * 熔断器打开后会对调用的 feign 服务直接调用降级服务，知道时间窗口的时间到达后才会重新尝试该服务是否可用。
     */
    @HystrixCommand(fallbackMethod = "randomExceptionHandlers")
    @GetMapping("randomException")
    public ApiResult<String> randomException() {
        ApiResult<String> result = eurekaUmService.randomException();
        printFeignInfo(result);
        return result;
    }

    public ApiResult<String> randomExceptionHandlers() {
        log.info("服务降级");
        return new ApiResult<>("EurekaCustomerService 熔断");
    }

    private void printFeignInfo(ApiResult<String> result) {
        if (result.getData() == null) {
            FAIL_COUNT = failCounter.getAndIncrement();
        } else {
            SUCCESS_COUNT = successCounter.getAndIncrement();
        }
        log.info("success : {} \t,fail: {}\t,result:{}", SUCCESS_COUNT, FAIL_COUNT, result.getData());
    }

    /**
     * 调用远程服务单独配置的方法超时时间无效,@HystrixCommand fallbackMethod 回调方法 blockingSimulationHandlers，
     * 是针对整个服务的降级，eurekaUmService 服务降级需要依靠 feignClient 实现自己的回调方法（client 中的回调是否被
     * 服务提供者提供的回调方法覆盖调，取决于谁先触发降级条件）。
     * <p>
     * 当一个方法内调用了多个服务只对第一个服务进行熔断，哈哈哈！
     * <p>
     * 注解  @HystrixCommand 只对当前服务产生的异常进行降级调用，对服务提供者的降级调用不会有影响。
     */
    @HystrixCommand(fallbackMethod = "blockingSimulationHandlers")
    @GetMapping("blockingSimulation")
    public ApiResult<String> blockingSimulation() {
        log.info("开始调用接口");
        ApiResult<String> result1 = eurekaUmService.randomException();
        // 只会对最后一个 feign 的调用进行熔断处理
        ApiResult<String> result = eurekaUmService.blockingSimulation();
        log.info(result1.toString());
        log.info(result.toString());
        return null;


    }

    public ApiResult<String> blockingSimulationHandlers() {
        log.info("服务降级");
        return new ApiResult<>("EurekaCustomerService 客户端超时触发服务降级");
    }
}
