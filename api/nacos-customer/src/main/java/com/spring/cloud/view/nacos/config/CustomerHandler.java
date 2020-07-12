package com.spring.cloud.view.nacos.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.cloud.common.until.ApiResult;

/**
 * Sentinel 公共处理类
 *
 * @author xuweizhi
 * @since 2020/07/11 17:13
 */
public class CustomerHandler {

    /**
     * 类似于 provider 的用法，方法必须是静态方法
     */
    public static ApiResult<String> customerHandlers(BlockException blockException) {
        return new ApiResult<>("自定义降级处理逻辑，与业务代码解耦 " );
    }
}
