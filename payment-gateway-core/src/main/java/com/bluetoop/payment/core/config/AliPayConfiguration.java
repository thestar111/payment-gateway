package com.bluetoop.payment.core.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <阿里配置>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/24 4:36 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Configuration
public class AliPayConfiguration {

    /**
     * Ali客户端
     *
     * @param aliPayProperties
     * @return
     */
    @Bean
    public AlipayClient alipayClient(@Autowired AliPayProperties aliPayProperties) {
        // 实例化支付宝支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayProperties.getGatewayUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getPrivateKey(),
                "json",
                aliPayProperties.getCharset(),
                aliPayProperties.getAlipayPublicKey(),
                aliPayProperties.getSignType());
        return alipayClient;
    }
}
