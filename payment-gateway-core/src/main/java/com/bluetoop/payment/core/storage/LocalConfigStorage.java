package com.bluetoop.payment.core.storage;

/**
 * <本地配置>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 2:02 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
public interface LocalConfigStorage {

    /**
     * 获取APPID
     *
     * @return
     */
    String getAppId();

    /**
     * 获取通知URL
     *
     * @return
     */
    String getNotifyUrl();

    /**
     * 获取跳转授权URL
     *
     * @return
     */
    String getAuthorizationCodeUrl();

    /**
     * 获取回调地址
     *
     * @return
     */
    String getReturnUrl();
}
