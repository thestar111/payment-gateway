package com.bluetoop.payment.core.exception;

import com.bluetoop.payment.core.error.IErrorCode;
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
    private IErrorCode errorCode;
}
