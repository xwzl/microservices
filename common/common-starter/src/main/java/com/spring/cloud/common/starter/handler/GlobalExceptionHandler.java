package com.spring.cloud.common.starter.handler;


import com.spring.cloud.common.until.ApiResult;
import com.spring.cloud.common.until.exception.ApiException;
import com.spring.cloud.common.until.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
    public void handleThrowable(Throwable e) throws IOException {
        log.error("exception", e);
        sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
    }

    @ExceptionHandler(ApiException.class)
    public void handleApiException(ApiException e) throws IOException {
        log.error("api exception", e);
        sendError(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResult<?> handleServiceException(ServiceException e) {
        log.error("service exception", e);
        return new ApiResult<>(e);
    }

    private void sendError(int code, String msg) throws IOException {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse response = servletRequestAttributes.getResponse();
        assert response != null;
        response.setStatus(code);
        response.getOutputStream().write(new ApiResult<>(code, msg).toString().getBytes(StandardCharsets.UTF_8));
        response.setContentType("application/json;charset=UTF-8");
    }


}
