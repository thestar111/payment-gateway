package com.bluetoop.payment.core.cons;

import com.bluetoop.payment.core.cons.IErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <支付异常>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 12:36 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
@Setter
public class PaymentException extends RuntimeException {

    /**
     * 错误码
     */
    private IErrorCode errorCode;

    /**
     * 构造方法
     *
     * @param errorCode
     */
    public PaymentException(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     *
     * @param message
     * @param errorCode
     */
    public PaymentException(String message, IErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     *
     * @param message
     * @param cause
     * @param errorCode
     */
    public PaymentException(String message, Throwable cause, IErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     *
     * @param cause
     * @param errorCode
     */
    public PaymentException(Throwable cause, IErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
