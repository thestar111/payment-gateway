package com.bluetoop.payment.api.pay.request;

import com.bluetoop.payment.api.pay.domain.ProductInfo;
import com.bluetoop.payment.core.cons.type.PayType;
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

    /** 订单ID */
    private String orderId;

    /** 金额 */
    private BigDecimal amount;

    /** 产品信息 */
    private ProductInfo productInfo;

    /** 支付类型 JSAPI、MWEB、ALIWAP {@linkplain PayType} */
    private String payType;

    /** IP地址 */
    private String ip;

    /** 微信JSAPI支付必填 */
    private String openId;

    /** 前端回调地址URL */
    private String frontUrl;

    /** 支付来源（1. PC/平板  2. Mobile） */
    private String source;
}
