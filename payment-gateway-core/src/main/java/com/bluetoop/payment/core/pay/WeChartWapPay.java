package com.bluetoop.payment.core.pay;

import cn.hutool.json.JSONUtil;
import com.bluetoop.payment.core.error.IErrorCode;
import com.bluetoop.payment.core.exception.PaymentException;
import com.bluetoop.payment.core.pay.domain.H5_info;
import com.bluetoop.payment.core.pay.request.WxPayRequest;
import com.bluetoop.payment.core.storage.LocalConfigStorage;
import com.bluetoop.payment.core.strategy.PayStrategy;
import com.bluetoop.payment.core.strategy.request.PayRequest;
import com.bluetoop.payment.core.strategy.response.PayResponse;
import com.bluetoop.payment.core.type.PayType;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;

import static com.github.binarywang.wxpay.constant.WxPayConstants.TradeType.MWEB;

/**
 * <微信WAP支付>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/23 2:54 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
public class WeChartWapPay extends PayStrategy<PayRequest, PayResponse> {

    @Autowired
    private WxPayService wxPayService;

    private LocalConfigStorage localConfigStorage;

    /**
     * 微信WAP支付
     *
     * @param wechartWapPayRequest
     * @return
     */
    @Override
    public PayResponse pay(PayRequest wechartWapPayRequest) {
        WxPayRequest wxPayRequest = new WxPayRequest();
        wxPayRequest.setBody(wechartWapPayRequest.getBody());
        //4.发起微信统一支付
        wxPayRequest.setTradeType(MWEB);
        wxPayRequest.setSpbillCreateIp(wechartWapPayRequest.getIp());
        wxPayRequest.setTotalFee(wechartWapPayRequest.getAmount().intValue());
        wxPayRequest.setSignType(WxPayConstants.SignType.MD5);
        wxPayRequest.setLimitPay(WxPayConstants.LimitPay.NO_CREDIT);
        wxPayRequest.setNotifyUrl(localConfigStorage.getNotifyUrl());
        wxPayRequest.setOutTradeNo(wechartWapPayRequest.getOutOrderNo());
        H5_info h5_info = wechartWapPayRequest.getH5_info();
        h5_info.setWap_url(localConfigStorage.getReturnUrl());
        wxPayRequest.setSceneInfo(h5_info.toString());
        WxPayMwebOrderResult wxPayMwebOrderResult = null;
        try {
            log.info("【WeChartWapPay】 invoke pay  ==========================>>>> request ：{}", JSONUtil.toJsonStr(wxPayRequest));
            wxPayMwebOrderResult = wxPayService.createOrder(wxPayRequest);
            log.info("【WeChartWapPay】 invoke pay  ==========================<<<< response ：{}", JSONUtil.toJsonStr(wxPayMwebOrderResult));
            if (null != wxPayMwebOrderResult) {
                PayResponse payResponse = new PayResponse();
                payResponse.setMwebUrl(wxPayMwebOrderResult.getMwebUrl() + "&redirect_url=" + URLEncoder.encode(localConfigStorage.getReturnUrl() , "UTF-8"));
                return payResponse;
            } else {
                // 微信支付失败
                throw new RuntimeException("微信支付失败");
            }
        } catch (WxPayException e) {
            log.error("【WeChartWapPay】 invoke pay failed. error : {}", ExceptionUtils.getRootCauseMessage(e));
            throw new PaymentException("微信支付失败", IErrorCode.PAYMENT_ERROR);
        } catch (Exception e) {
            log.error("【WeChartWapPay】 invoke pay failed. error : {}", ExceptionUtils.getRootCauseMessage(e));
            throw new PaymentException("微信支付失败", IErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 支付类型
     *
     * @return
     */
    @Override
    public PayType payType() {
        return PayType.WXWAP;
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
