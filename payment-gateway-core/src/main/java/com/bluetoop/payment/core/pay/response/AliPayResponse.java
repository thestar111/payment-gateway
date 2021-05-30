/**
 * 文 件 名:  AliPayResponse
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  23:28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.pay.response;

import com.bluetoop.payment.core.strategy.response.PayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <一句话功能简述>
 *
 * @author zhouping
 * @version 1.0
 * @date 2021/5/30 23:28
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AliPayResponse extends PayResponse {

    /** 返回体 */
    private String body;

    /** 卖家ID */
    private String sellerId;

    /** 订单ID */
    private String tradeNo;

    /** 二维码 */
    private String qrcode;
}
