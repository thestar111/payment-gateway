/**
 * 文 件 名:  PayQrCodeResonpse
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  15:26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.pay.response;

import com.bluetoop.payment.core.strategy.response.PayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <二维码支付响应结果>
 *
 * @author zhouping
 * @version 1.0
 * @date 2021/5/30 15:26
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayQrCodeResonpse extends PayResponse {

    /** 生成的二维码的字节数组 */
    private byte[] bytes;

    /** 二维码URL连接, 格式：weixin://wxpay/bizpayurl?sr=123456  商户将该链接生成二维码图片 */
    private String codeUrl;
}
