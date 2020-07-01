package com.spring.cloud.common.until.exception;

import com.spring.cloud.common.until.enums.HttpStatusEnum;
import lombok.Getter;

/**
 * api 异常
 *
 * @author xuweizhi
 * @since 2020/07/01 23:55
 */
@Getter
public class ApiException extends RuntimeException {

    private Integer code = HttpStatusEnum.OK.value();

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApiException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ApiException(HttpStatusEnum hs) {
        super(hs.getReasonPhrase());
        this.code = hs.value();
    }

    public ApiException(HttpStatusEnum hs, Throwable e) {
        super(hs.getReasonPhrase(), e);
        this.code = hs.value();
    }
}
