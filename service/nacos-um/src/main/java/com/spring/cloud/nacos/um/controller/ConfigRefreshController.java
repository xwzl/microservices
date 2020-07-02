package com.spring.cloud.nacos.um.controller;

import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.nacos.um.module.BookVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Config 线刷验证
 *
 * @author xuweizhi
 * @since 2020/07/02 21:41
 */
@RefreshScope
@RestController
@RequestMapping("config")
public class ConfigRefreshController {

    @Value("${application.tips}")
    public String tips;

    @Resource
    private BookVO bookVO;

    /**
     * 不启动应用刷新变量值
     */
    @GetMapping("refresh")
    public ApiResult<String> refresh() {
        return new ApiResult<>(tips);
    }

    /**
     * 不启动应用刷新变量值
     */
    @GetMapping("refreshModule")
    public ApiResult<String> refreshModule() {
        return new ApiResult<>(bookVO.toString());
    }
}
