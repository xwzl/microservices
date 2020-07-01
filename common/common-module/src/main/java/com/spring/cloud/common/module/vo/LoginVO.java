package com.spring.cloud.common.module.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录 VO
 *
 * @author xuweizhi
 * @since 2020/07/01 23:35
 */
@Data
public class LoginVO implements Serializable {

    /**
     * 用户账号不能为空
     */
    @NotBlank(message = "用户账号不能为空")
    private String account;

    /**
     * 用户密码不能为空
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;

}
