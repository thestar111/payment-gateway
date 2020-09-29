package com.bluetoop.payment.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <微信支付属性配置>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 2:33 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
@Configuration
@PropertySource(value = "classpath:wxpay.properties")
@ConfigurationProperties(prefix = "wechart")
public class WxPayProperties {

    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
     */
    private String subAppId;

    /**
     * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
     */
    private String subMchId;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    /**
     * 通知URL
     */
    private String notify;

    /**
     * 跳转商户URL
     */
    private String returnUrl;
}
