package com.bluetoop.payment.core.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.bluetoop.payment.core.cons.IErrorCode;
import com.bluetoop.payment.core.cons.PaymentException;
import com.bluetoop.payment.core.cons.type.PayType;
import com.bluetoop.payment.core.pay.response.AliPayResponse;
import com.bluetoop.payment.core.storage.LocalConfigStorage;
import com.bluetoop.payment.core.strategy.PayStrategy;
import com.bluetoop.payment.core.strategy.request.PayRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * <阿里WAP支付>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/23 2:55 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
public class AliWebPay extends PayStrategy<PayRequest, AliPayResponse> {

    @Autowired
    private AlipayClient alipayClient;

    private LocalConfigStorage localConfigStorage;

    /**
     * 阿里支付
     *
     * @param   payRequest
     * @return  返回Form表单数据
     */
    @Override
    public AliPayResponse pay(PayRequest payRequest) {
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(payRequest.getBody());
        model.setSubject(payRequest.getProductName());
        // 商户订单号 就是商户后台生成的订单号
        model.setOutTradeNo(payRequest.getOutOrderNo());
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天 (屁股后面的字母一定要带，不然报错)
        model.setTimeoutExpress("30m");
        model.setProductCode(payType().getProductCode());
        // 订单总金额 ，默认单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        model.setTotalAmount(payRequest.getAmount().divide(new BigDecimal(100)).toString());
        alipayTradePagePayRequest.setBizModel(model);
        alipayTradePagePayRequest.setNotifyUrl(localConfigStorage.getNotifyUrl());
        alipayTradePagePayRequest.setReturnUrl(localConfigStorage.getReturnUrl());
        // 通过api的方法请求阿里接口获得反馈
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(alipayTradePagePayRequest);
            if (response.isSuccess()) {
                AliPayResponse payResponse = new AliPayResponse();
                payResponse.setBody(response.getBody());
                payResponse.setOutOrderNo(response.getOutTradeNo());
                payResponse.setSellerId(response.getSellerId());
                payResponse.setTradeNo(response.getTradeNo());
                return payResponse;
            } else {
                throw new PaymentException("支付宝PC支付失败", IErrorCode.PAYMENT_ERROR);
            }
        } catch (AlipayApiException e) {
            log.error("【AliWebPay】 invoke  pay failed. ====================<<<< error : {}", ExceptionUtils.getRootCauseMessage(e));
            throw new PaymentException("支付宝PC支付失败", IErrorCode.PAYMENT_ERROR);
        }
    }

    /**
     * 支付类型
     *
     * @return
     */
    @Override
    public PayType payType() {
        return PayType.ALIPAY;
    }

    /**
     * 本地缓存信息
     *
     * @param localConfigStorage
     */
    @Override
    public void setLocalConfigStorage(LocalConfigStorage localConfigStorage) {
        this.localConfigStorage = localConfigStorage;
    }
}
