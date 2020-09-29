package com.bluetoop.payment.core.pay.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <微信H5支付信息模型>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/24 4:25 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
@Setter
public class H5ScencInfoModel implements Serializable {

    /**
     * 商户网站信息
     */
    private H5_info h5_info;
}
