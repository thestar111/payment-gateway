package com.bluetoop.payment.core.common;

import com.bluetoop.payment.core.error.IErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <一句话功能描述>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 3:02 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
@Setter
@NoArgsConstructor
public class Result<T> {
    /** 错误码 */
    private int code;

    /** 描述信息 */
    private String msg;

    /** 返回数据 */
    private T data;

    /**
     * 设置Code
     *
     * @param errorCode
     */
    public void setErrorCode(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
