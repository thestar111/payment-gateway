package com.bluetoop.payment.core.type;

import lombok.Getter;

/**
 * <一句话功能描述>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 4:14 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum OAuthType {

    /** 弹出授权码 */
    SNS_USER_INFO("snsapi_userinfo"),

    /** 不弹出授权码：只能获取用户OPENID */
    SNS_BASE_INFO("snsapi_base");

    /**
     * 授权类型
     */
    private String type;

    OAuthType(String type) {
        this.type = type;
    }
}
