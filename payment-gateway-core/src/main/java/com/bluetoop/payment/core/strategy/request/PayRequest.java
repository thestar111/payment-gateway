package com.bluetoop.payment.core.strategy.request;

import com.bluetoop.payment.core.pay.domain.H5_info;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <支付请求对象>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 3:16 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
public class PayRequest implements Serializable {

    /** 商户后台订单ID */
    private String outOrderNo;

    /** 订单金额：分 */
    private BigDecimal amount;

    /** IP地址 */
    private String ip;

    /** 产品ID */
    private String productId;

    /** 产品名称 */
    private String productName;

    /** 名称 */
    private String attach;

    /** 用户OPENID */
    private String openId;

    /** WAP支付H5信息 */
    private H5_info h5_info;

    /** 产品信息 */
    private String body;

    /** 是否支持使用信用卡 */
    private Boolean enableCredit;

    /** 支付过期时间 （yyyyMMddHHmmss） */
    private String expireTime;

}
