package com.bluetoop.payment.core.strategy.response;

import lombok.Data;

import java.io.Serializable;

/**
 * <支付响应>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/29 4:03 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
public class PayResponse implements Serializable {

    /** 外部订单编号 （商户订单号）*/
    private String outOrderNo;

    /** 微信支付订单ID */
    private String transactionId;

    /** 公众号ID */
    private String appId;

    /** 账户微信OpenID */
    private String openId;

    /** 时间戳 */
    private String timeStamp;

    /** 随机字符串 */
    private String nonceStr;

    /** 预支付ID */
    private String prepayId;

    /** 签名类型（默认：SHA1 新版本：MD5） */
    private String signType;

    /** 签名 */
    private String sign;

    /** 支付跳转连接（H5支付需要） */
    private String mwebUrl;

    /** 交易类型 @{linkp} TradeType */
    private String tradeType;

    /** 交易完成时间 */
    private String endTime;

    /** 支付方式 */
    private String bankType;

    /** 总金额 */
    private Integer totalFee;

    /** 代金券金额 */
    private Integer couponFee;
}
