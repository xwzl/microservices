package com.spring.cloud.eureka.um.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.common.until.enums.ServiceCodeEnum;
import com.spring.cloud.common.until.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 熔断:回调写 FeignClient 与业务解耦，不建议在此处写服务降级逻辑。
     */
    @GetMapping("randomException")
    @HystrixCommand(fallbackMethod = "randomExceptionHandler")
    public ApiResult<String> randomException() {
        log.info("method start ");
        // 伪随机
        if (Math.random() > 0.5) {
            throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "hystrix fuse test .........");
        }
        return new ApiResult<>("Tomcat ThreadPool:" + Thread.currentThread().getName());
    }

    public ApiResult<String> randomExceptionHandler() {
        return new ApiResult<>("randomException 服务降级，调用者处理降级逻辑!");
    }

    /**
     * 服务降级：
     * <ul>
     *     <li>执行 construct() 或 run() 抛出异常</li>
     *     <li>熔断器打开导致命令短路</li>
     *     <li>命令的线程池和队列或信号量的容量超额，命令被拒绝</li>
     *     <li>命令执行超时</li>
     * </ul>
     * <p>
     * 熔断：请求数到达设定阈值后且失败次数大于设定百分比会进行熔断
     * <p>
     * 客服端使用 @HystrixCommand，客户端的熔断失效。
     */
    @GetMapping("blockingSimulation")
    @HystrixCommand(fallbackMethod = "blockingSimulationHandler", commandProperties = {
            // 方法执行时间超过两秒钟将触发服务降级，服务降级会触发回调方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
    })
    public ApiResult<String> blockingSimulation() {
        //long time = (long) (Math.random() * 10000);
        long time = (long) (Math.random() * 300);
        try {
            log.info("Thread sleep time {}", time);
            // ps: time 大于超时时间直接抛出异常，进行过方法拦截么？
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "hystrix fuse test .........");
        }
        return new ApiResult<>("Tomcat ThreadPool:" + Thread.currentThread().getName() + " time:" + time);
    }

    public ApiResult<String> blockingSimulationHandler() {
        return new ApiResult<>("EurekaUmService 服务端超时触发服务降级");
    }
}
