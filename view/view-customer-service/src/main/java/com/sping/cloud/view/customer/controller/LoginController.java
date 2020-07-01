package com.sping.cloud.view.customer.controller;

import com.spring.cloud.common.module.feign.UmService;
import com.spring.cloud.common.module.vo.LoginVO;
import com.spring.cloud.common.until.ApiResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:16
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private UmService umService;

    @PostMapping("login")
    public ApiResult<String> login(@RequestBody @Validated LoginVO loginVO) {
        return umService.login(loginVO);
    }

}
