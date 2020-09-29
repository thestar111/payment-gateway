package com.bluetoop.payment.core.context;

import com.bluetoop.payment.core.config.AliPayProperties;
import com.bluetoop.payment.core.config.WxPayProperties;
import com.bluetoop.payment.core.error.IErrorCode;
import com.bluetoop.payment.core.exception.PaymentException;
import com.bluetoop.payment.core.pay.AliWapPay;
import com.bluetoop.payment.core.pay.WeChartPay;
import com.bluetoop.payment.core.pay.WeChartWapPay;
import com.bluetoop.payment.core.storage.InMemoryConfigStorage;
import com.bluetoop.payment.core.strategy.PayStrategy;
import com.bluetoop.payment.core.type.PayType;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static com.bluetoop.payment.core.type.PayType.*;

/**
 * <支付上下文>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/29 11:50 上午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
@Configuration
public class PaymentContext implements InitializingBean {

    /**
     * 支付实例集合
     */
    private static Map<PayType, PayStrategy> PAY_STRATEGY_MAP = Maps.newConcurrentMap();

    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private AliPayProperties aliPayProperties;

    /**
     * 创建对应的支付实例
     *
     * @param type
     * @return
     */
    public PayStrategy creator(String type) {
        PayType payType = PayType.valueOf(type);
        if (payType == null) {
            throw new PaymentException("支付类型不存在", IErrorCode.SYSTEM_ERROR);
        }
        return PAY_STRATEGY_MAP.get(payType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("================初始化支付实例======================");
        PAY_STRATEGY_MAP.put(ALIPAY, aliWapPay(aliPayProperties));
        PAY_STRATEGY_MAP.put(JSAPI, wechartPay(wxPayProperties));
        PAY_STRATEGY_MAP.put(WXWAP, wechartWayPay(wxPayProperties));
        log.info("================支付实例初始化完成======================");
    }

    @Bean
    public PayStrategy wechartWayPay(WxPayProperties wxPayProperties) {
        PayStrategy wechartWapPay = new WeChartWapPay();
        InMemoryConfigStorage inMemoryConfigStorage = new InMemoryConfigStorage();
        inMemoryConfigStorage.setNotifyUrl(wxPayProperties.getNotify());
        inMemoryConfigStorage.setReturnUrl(wxPayProperties.getReturnUrl());
        wechartWapPay.setLocalConfigStorage(inMemoryConfigStorage);
        return wechartWapPay;
    }

    @Bean
    public PayStrategy wechartPay(WxPayProperties wxPayProperties) {
        PayStrategy wechartPay = new WeChartPay();
        InMemoryConfigStorage inMemoryConfigStorage = new InMemoryConfigStorage();
        inMemoryConfigStorage.setNotifyUrl(wxPayProperties.getNotify());
        inMemoryConfigStorage.setReturnUrl(wxPayProperties.getReturnUrl());
        wechartPay.setLocalConfigStorage(inMemoryConfigStorage);
        return wechartPay;
    }

    @Bean
    public PayStrategy aliWapPay(@Autowired AliPayProperties aliPayProperties) {
        InMemoryConfigStorage inMemoryConfigStorage = new InMemoryConfigStorage();
        inMemoryConfigStorage.setNotifyUrl(aliPayProperties.getNotifyUrl());
        inMemoryConfigStorage.setReturnUrl(aliPayProperties.getReturnUrl());
        return new AliWapPay();
    }
}
