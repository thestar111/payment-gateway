package com.bluetoop.payment.core.pay;

import cn.hutool.json.JSONUtil;
import com.bluetoop.payment.core.cons.IErrorCode;
import com.bluetoop.payment.core.cons.PaymentException;
import com.bluetoop.payment.core.cons.type.PayType;
import com.bluetoop.payment.core.pay.request.MicropayRequest;
import com.bluetoop.payment.core.pay.request.WxPayRequest;
import com.bluetoop.payment.core.storage.LocalConfigStorage;
import com.bluetoop.payment.core.strategy.PayStrategy;
import com.bluetoop.payment.core.strategy.response.PayResponse;
import com.bluetoop.payment.core.utils.StringUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static com.bluetoop.payment.core.cons.type.Device.WEB;
import static com.github.binarywang.wxpay.constant.WxPayConstants.TradeType.MICROPAY;

/**
 * <微信刷卡支付>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/23 2:53 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
public class WeChartMicroPay extends PayStrategy<MicropayRequest, PayResponse> {

    @Autowired
    private WxPayService wxPayService;

    private LocalConfigStorage localConfigStorage;

    /**
     * 微信刷卡支付
     * 收银员使用扫码设备读取微信用户刷卡授权码以后，二维码或条码信息传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
     *    提醒1：提交支付请求后微信会同步返回支付结果。当返回结果为“系统错误”时，商户系统等待5秒后调用【查询订单API】，查询支付实际交易结果；当返回结果为“USERPAYING”时，商户系统可设置间隔时间(建议10秒)重新查询支付结果，直到支付成功或超时(建议30秒)；
     *    提醒2：在调用查询接口返回后，如果交易状况不明晰，请调用【撤销订单API】，此时如果交易失败则关闭订单，该单不能再支付成功；如果交易成功，则将扣款退回到用户账户。当撤销无返回或错误时，请再次调用。
     *    注意：请勿扣款后立即调用【撤销订单API】,建议至少15秒后再调用。撤销订单API需要双向证书。
     *
     *
     * @param micropayRequest
     * @return
     */
    @Override
    public PayResponse pay(MicropayRequest micropayRequest) {
        WxPayMicropayRequest wxPayMicropayRequest = new WxPayMicropayRequest();
        wxPayMicropayRequest.setSignType(WxPayConstants.SignType.MD5);
        wxPayMicropayRequest.setLimitPay(micropayRequest.getLimitPay());
        wxPayMicropayRequest.setOutTradeNo(micropayRequest.getOutOrderNo());
        wxPayMicropayRequest.setSpbillCreateIp(micropayRequest.getIp());
        wxPayMicropayRequest.setBody(micropayRequest.getAttach());
        wxPayMicropayRequest.setAttach(micropayRequest.getAttach());
        wxPayMicropayRequest.setTotalFee(micropayRequest.getAmount().intValue());
        wxPayMicropayRequest.setAuthCode(micropayRequest.getAuthCode());
        wxPayMicropayRequest.setTimeExpire(micropayRequest.getExpireTime());
        WxPayMicropayResult micropayResult = null;
        try {
            log.info("【WeChartPay】 invoke pay  ==========================>>>> request ：{}", JSONUtil.toJsonStr(wxPayMicropayRequest));
            micropayResult = wxPayService.micropay(wxPayMicropayRequest);
            log.info("【WeChartPay】 invoke pay  ==========================<<<< response ：{}", JSONUtil.toJsonStr(micropayResult));
            if (!Objects.isNull(micropayResult)) {
                PayResponse payResponse = new PayResponse();
                payResponse.setAppId(micropayResult.getAppid());
                payResponse.setTransactionId(micropayResult.getTransactionId());
                payResponse.setNonceStr(micropayResult.getNonceStr());
                payResponse.setOutOrderNo(micropayResult.getOutTradeNo());
                payResponse.setEndTime(micropayResult.getTimeEnd());
                payResponse.setTradeType(micropayResult.getTradeType());
                payResponse.setOpenId(micropayResult.getOpenid());
                payResponse.setBankType(micropayResult.getBankType());
                payResponse.setTotalFee(micropayResult.getTotalFee());
                payResponse.setCouponFee(micropayResult.getCouponFee());
                return payResponse;
            } else {
                // 微信支付失败
                throw new PaymentException("微信刷卡支付失败", IErrorCode.WX_PAY_ERROR);
            }
        } catch (WxPayException e) {
            log.error("【WeChartPay】 invoke jsapi pay failed. ====================<<<< error : {}", ExceptionUtils.getRootCauseMessage(e));
            throw new PaymentException("微信刷卡支付失败", IErrorCode.PAYMENT_ERROR);
        }
    }

    /**
     * 支付类型
     *
     * @return
     */
    @Override
    public PayType payType() {
        return PayType.WXMICRO;
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
