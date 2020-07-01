package com.spring.cloud.common.until;

import com.spring.cloud.common.until.enums.ServiceCodeEnum;
import lombok.Data;

/**
 * 统一封装返回对象
 *
 * @author xuweizhi
 * @since 2020/07/01 23:31
 */
@Data
public class ApiResult<T> {

    private Integer code;

    private String message;

    private T data;

    public ApiResult() {
    }

    public ApiResult(T data) {
        this.code= ServiceCodeEnum.SUCCESS.getCode();
        this.message=ServiceCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
