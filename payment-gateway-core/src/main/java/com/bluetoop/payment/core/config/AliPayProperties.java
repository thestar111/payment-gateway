package com.bluetoop.payment.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

/**
 * <阿里支付配置>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/24 4:32 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:/alipay.properties")
@ConfigurationProperties(prefix = "ali")
public class AliPayProperties implements Serializable {
    /**
     * APPID
     */
    private String appId;
    /**
     * 编码
     */
    private String charset;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 私有秘钥
     */
    private String privateKey;
    /**
     * 公共秘钥
     */
    private String alipayPublicKey;
    /**
     * 异步回调地址
     */
    private String notifyUrl;
    /**
     * 同步回调地址
     */
    private String returnUrl;
    /**
     * 请求网关地址
     */
    private String gatewayUrl;
}
