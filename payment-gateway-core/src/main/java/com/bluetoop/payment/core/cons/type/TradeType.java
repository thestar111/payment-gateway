/**
 * 文 件 名:  TradeType
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  16:26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.cons.type;

import lombok.Getter;

/**
 * <微信交易类型>
 *
 * @author zhouping
 * @version 1.0
 * @date 2021/5/30 16:26
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum TradeType {

    MICROPAY("付款码支付"), JSAPI("JSAPI支付"), NATIVE("Native支付"), APP("APP支付");

    private String desc;

    TradeType(String desc) {
        this.desc = desc;
    }
}
