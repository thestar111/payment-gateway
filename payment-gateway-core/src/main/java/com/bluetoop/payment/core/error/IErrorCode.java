package com.bluetoop.payment.core.error;


import lombok.Getter;

/**
 * <错误码>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 12:37 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum IErrorCode {
    /**
     * 系统异常
     */
    SYSTEM_ERROR(9999, "System error."),
    /**
     * 支付异常
     */
    PAYMENT_ERROR(1001, "pay error.");

    /**
     * 错误码
     */
    private int code;

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 构造方法
     *
     * @param code
     * @param msg
     */
    IErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
