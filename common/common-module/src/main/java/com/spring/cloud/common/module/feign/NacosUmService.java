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
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;

/**
 * @author xuweizhi
 * @since 2020/07/01 23:26
 */
@Component
@FeignClient(value = "nacos-um", contextId = "service-interface-one", fallback = NacosUmService.NacosUmServiceImpl.class)
public interface NacosUmService {

    @PostMapping("/login/login")
    ApiResult<String> login(@RequestBody @Validated LoginVO loginVO);

    @GetMapping("/rest/discovery")
    DiscoveryClient discoveryInfo();

    @GetMapping("/rest/restBalance")
    ApiResult restBalance() throws URISyntaxException;

    @GetMapping("/rest/balance")
    ApiResult<Integer> balance();


    @GetMapping("/sentinel/fallback")
    ApiResult<String> fallback(@RequestParam("id") String id);

    @Component
    class NacosUmServiceImpl implements NacosUmService {

        @Override
        public ApiResult<String> login(LoginVO loginVO) {
            return null;
        }

        @Override
        public DiscoveryClient discoveryInfo() {
            return null;
        }

        @Override
        public ApiResult restBalance() throws URISyntaxException {
            return null;
        }

        @Override
        public ApiResult<Integer> balance() {
            return new ApiResult<>(1);
        }

        @Override
        public ApiResult<String> fallback(String id) {
            return new ApiResult<>("feign client 降级方法");
        }

    }
}
