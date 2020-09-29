package com.bluetoop.payment.core.type;

import lombok.Getter;

/**
 * <一句话功能描述>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 4:03 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum RequestHeader {
    X_REQUEST_ID("X-Request-ID"),
    X_APPLICATION_CONTEXT("X-Application-Context"),
    X_VIA("lantuo-via");

    /**
     * 请求头名称
     */
    private String name;

    RequestHeader(String name) {
        this.name = name;
    }
}
