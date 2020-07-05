package com.spring.cloud.common.module.feign;

import com.spring.cloud.common.module.vo.LoginVO;
import com.spring.cloud.common.until.ApiResult;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xuweizhi
 * @since 2020/07/01 23:26
 */
@Component
@FeignClient(value = "eureka-um", fallback = EurekaUmService.EurekaUmServiceImpl.class)
public interface EurekaUmService {

    @PostMapping("/login/login")
    ApiResult<String> login(@RequestBody @Validated LoginVO loginVO);

    @GetMapping("/rest/discovery")
    DiscoveryClient discoveryInfo();

    /**
     * 熔断测试
     */
    @GetMapping("/hystrix/randomException")
    ApiResult<String> randomException();

    @GetMapping("/hystrix/blockingSimulation")
    ApiResult<String> blockingSimulation();

    @GetMapping("/rest/balance")
    ApiResult<Integer> balance();

    @Component
    class EurekaUmServiceImpl implements EurekaUmService {

        @Override
        public ApiResult<String> login(LoginVO loginVO) {
            return null;
        }

        @Override
        public DiscoveryClient discoveryInfo() {
            return null;
        }

        /**
         * 客户端加入 @HystrixCommand 注解，熔断器有效。
         */
        @Override
        public ApiResult<String> randomException() {
            return new ApiResult<>(" open feign randomException 服务降级");
        }

        @Override
        public ApiResult<String> blockingSimulation() {
            return new ApiResult<>(" open feign blockingSimulation 服务降级");
        }

        @Override
        public ApiResult<Integer> balance() {
            return null;
        }
    }

}
