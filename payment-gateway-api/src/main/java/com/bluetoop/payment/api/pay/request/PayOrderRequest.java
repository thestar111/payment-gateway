package com.bluetoop.payment.api.pay.request;

import com.bluetoop.payment.api.pay.domain.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <订单支付请求实体>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 4:28 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
public class PayOrderRequest implements Serializable {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 产品信息
     */
    private ProductInfo productInfo;
    /**
     * 支付类型 JSAPI、MWEB、ALIWAP
     * {@linkplain com.bluetoop.payment.core.type.PayType}
     */
    private String payType;
    /**
     * IP地址
     */
    private String ip;

    /**
     * 微信JSAPI支付必填
     */
    private String openId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PayOrderRequest{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", productInfo=").append(productInfo);
        sb.append(", payType='").append(payType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
