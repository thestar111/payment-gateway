package com.bluetoop.payment.core.storage;

import com.bluetoop.payment.core.storage.LocalConfigStorage;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <微信本地配置>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 2:11 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Setter
public class InMemoryConfigStorage implements LocalConfigStorage, Serializable {

    /**
     * 回调URL
     */
    private volatile String notifyUrl;

    /**
     * 授权CODE
     */
    private volatile String authorizationCodeUrl;

    /**
     * 商户支付完成跳转地址
     */
    private volatile String returnUrl;

    /**
     * APPID
     */
    private volatile String appid;

    @Override
    public String getAppId() {
        return this.appid;
    }

    @Override
    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    @Override
    public String getAuthorizationCodeUrl() {
        return this.authorizationCodeUrl;
    }

    @Override
    public String getReturnUrl() {
        return this.returnUrl;
    }
}
