package com.spring.cloud.eureka.um.controller;

import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.common.module.vo.LoginVO;
import com.spring.cloud.common.until.enums.ServiceCodeEnum;
import com.spring.cloud.common.until.exception.ServiceException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录模块
 *
 * @author xuweizhi
 * @since 2020/07/01 23:16
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @PostMapping("login")
    public ApiResult<String> login(@RequestBody @Validated LoginVO loginVO) {
        if (loginVO.getAccount().equals("root") && loginVO.getPassword().equals("123456")) {
            return new ApiResult<>("登录成功");
        }
        throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "用户名或者密码错误");
    }

}
