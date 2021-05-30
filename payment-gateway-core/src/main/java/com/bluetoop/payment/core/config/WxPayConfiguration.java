package com.bluetoop.payment.core.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <微信支付配置>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 2:31 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
public class WxPayConfiguration {

    /**
     * 微信支付
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "wxpay.enable", havingValue = "true", matchIfMissing = true)
    public WxPayService wxService(@Autowired WxPayProperties wxPayProperties) {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(wxPayProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(wxPayProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(wxPayProperties.getMchKey()));
        payConfig.setSubAppId(StringUtils.trimToNull(wxPayProperties.getSubAppId()));
        payConfig.setSubMchId(StringUtils.trimToNull(wxPayProperties.getSubMchId()));
        payConfig.setKeyPath(StringUtils.trimToNull(wxPayProperties.getKeyPath()));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
