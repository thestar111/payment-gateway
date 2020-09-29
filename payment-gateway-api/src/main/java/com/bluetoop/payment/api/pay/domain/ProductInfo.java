package com.bluetoop.payment.api.pay.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <产品信息>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 4:30 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Data
public class ProductInfo implements Serializable {

    /** 产品ID */
    private String productId;
    /** 产品名称 */
    private String productName;
    /** 产品描述 */
    private String body;
}
