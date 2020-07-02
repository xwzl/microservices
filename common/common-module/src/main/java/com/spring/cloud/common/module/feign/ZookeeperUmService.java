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
@FeignClient("zookeeper-um")
public interface ZookeeperUmService {

    @PostMapping("/login/login")
    ApiResult<String> login(@RequestBody @Validated LoginVO loginVO);

    @GetMapping("/rest/discovery")
    DiscoveryClient discoveryInfo();
}
