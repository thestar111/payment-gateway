/**
 * 文 件 名:  MicropayRequest
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  15:52
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.pay.request;

import com.bluetoop.payment.core.strategy.request.PayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * <POST刷卡支付>
 *
 * @author zhouping
 * @version 1.0
 * @date 2021/5/30 15:52
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MicropayRequest extends PayRequest {

    /** 付款码 */
    private String authCode;

    /**
     * 获取限制支付方式
     *
     * @return
     */
    public String getLimitPay() {
        if (Objects.isNull(getEnableCredit()) || Boolean.FALSE == getEnableCredit()) {
            return WxPayConstants.LimitPay.NO_CREDIT;
        }
        return null;
    }
}