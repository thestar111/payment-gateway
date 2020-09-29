/**
 * 文 件 名:  WxMpProperties
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Pine.Z
 * 修改时间:  2019/7/23
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.config;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <微信公众号属性实体>
 *
 * @author zhouping
 * @version 2020/09/19
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "wechart.account")
public class WxMpProperties {

    /**
     * 公众号配置信息集合
     */
    private List<MpConfig> configs = Lists.newArrayList();

    /**
     * 公众号配置信息
     */
    @Getter
    @Setter
    public static class MpConfig {
        /**
         * 微信公众号appid
         */
        private String appId;

        /**
         * 微信公众号app secret
         */
        private String appSecret;

        /**
         * 微信公众号Token
         */
        private String token;

        /**
         * 微信公众号的EncodingAESKey
         */
        private String aesKey;

        /**
         * 授权URL
         */
        private String oauth;
    }
}
