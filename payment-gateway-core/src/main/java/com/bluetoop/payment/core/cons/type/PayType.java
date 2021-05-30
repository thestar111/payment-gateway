package com.bluetoop.payment.core.cons.type;

import lombok.Getter;

/**
 * <支付类型>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/23 2:47 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum PayType {

    /** 微信公众号支付 */
    JSAPI("JSAPI"),
    /** 微信H5支付 */
    WXWAP("WXWAP"),
    /** 微信扫码 */
    WXPAY_QCODE("WXPAY_QCODE"),
    /** 微信刷卡 */
    WXMICRO("MICRO"),
    /** 支付宝H5 */
    ALIPAY("QUICK_WAP_WAY"),
    /** 支付宝WEB */
    ALIPAY_WEB("FAST_INSTANT_TRADE_PAY"),
    /** 支付宝扫码 */
    ALIPAY_QCODE("ALIPAY_QCODE");

    /** 产品编码 */
    private String productCode;

    PayType(String productCode) {
        this.productCode = productCode;
    }
}
