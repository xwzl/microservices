package com.spring.cloud.view.consule.controller;

import com.spring.cloud.common.module.feign.ConsulUmService;
import com.spring.cloud.common.module.vo.LoginVO;
import com.spring.cloud.common.until.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:16
 */
@Api
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private ConsulUmService consulUmService;

    @PostMapping("login")
    @ApiOperation("login")
    public ApiResult<String> login(@RequestBody @Validated LoginVO loginVO) {
        return consulUmService.login(loginVO);
    }

}
