package com.spring.cloud.common.starter.handler;


import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.common.until.exception.ApiException;
import com.spring.cloud.common.until.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * 全局异常同一处理
 *
 * @author xuweizhi
 * @since 2020/07/01 23:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiResult<?> handleThrowable(Throwable e) throws IOException {
        log.error("exception", e);
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ApiResult<?> handleApiException(ApiException e) throws IOException {
        log.error("api exception", e);
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResult<?> handleServiceException(ServiceException e) {
        log.error("service exception", e);
        return new ApiResult<>(e.getCode(), e.getMessage());
    }


}
