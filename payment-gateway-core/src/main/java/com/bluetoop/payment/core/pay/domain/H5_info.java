package com.bluetoop.payment.core.pay.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <微信H5描述信息>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/24 4:22 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
@Setter
public class H5_info implements Serializable {
    private String type;
    private String wap_url;
    private String wap_name;
}
