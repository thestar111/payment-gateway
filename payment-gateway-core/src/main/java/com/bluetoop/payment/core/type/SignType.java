/**
 * 文 件 名:  SignType
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  15:08
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.type;

import lombok.Getter;

/**
 * <加密方式>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/19 15:08
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
public enum SignType {
    /**
     * HMAC-SHA256 加密
     */
    HMACSHA256 ("HMAC-SHA256"),

    /**
     * MD5 加密
     */
    MD5 ("MD5");

    SignType(String type)
    {
        this.type = type;
    }

    private final String type;

    public String getType()
    {
        return type;
    }
}
