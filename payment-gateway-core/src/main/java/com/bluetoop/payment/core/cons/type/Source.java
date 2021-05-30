/**
 * 文 件 名:  Source
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  23:12
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.cons.type;

import lombok.Getter;

/**
 * <来源>
 *
 * @author zhouping
 * @version 1.0
 * @date 2021/5/27 23:12
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum Source {

    PC(1, "PC/平板"), MOBILE(2, "手机");

    /** 支付来源 */
    private Integer code;

    /** 描述 */
    private String desc;

    Source(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
