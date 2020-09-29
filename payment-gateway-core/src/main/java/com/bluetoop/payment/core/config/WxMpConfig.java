/**
 * 文 件 名:  WxMpConfig
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Pine.Z
 * 修改时间:  2019/7/26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.config;

import com.bluetoop.payment.core.error.IErrorCode;
import com.bluetoop.payment.core.exception.PaymentException;
import com.bluetoop.payment.core.storage.InMemoryConfigStorage;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <微信公众号配置>
 *
 * @author zhouping
 * @version 2020/09/19
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfig implements InitializingBean {

    /**
     * 微信公众号配置服务
     */
    private static Map<String, WxMpService> mpServices = Maps.newConcurrentMap();
    /**
     * 授权URL配置
     */
    private static Map<String, InMemoryConfigStorage> config = Maps.newConcurrentMap();

    @Autowired
    private WxMpProperties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        init(properties);
    }

    /**
     * 初始化配置微信公众号信息
     *
     * @param properties
     */
    public void init(WxMpProperties properties) {
        final List<WxMpProperties.MpConfig> configs = properties.getConfigs();
        if (configs == null) {
            log.error("【WxMpConfig】 loading wx config failed. WxMpProperties : {}", properties);
            throw new PaymentException("微信基础配置加载失败.", IErrorCode.SYSTEM_ERROR);
        }

        // 设置一个微信APPID对应一个公众号服务
        mpServices = configs.stream().map(a -> {
            WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
            configStorage.setAppId(a.getAppId());
            configStorage.setSecret(a.getAppSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());

            WxMpService service = new WxMpServiceImpl();
            service.setWxMpConfigStorage(configStorage);
            return service;
        }).collect(Collectors.toMap(s -> s.getWxMpConfigStorage().getAppId(), a -> a, (o, n) -> o));

        // 设置授权URL
        config = configs.stream().map(s -> {
            InMemoryConfigStorage inMemoryConfigStorage = new InMemoryConfigStorage();
            inMemoryConfigStorage.setAuthorizationCodeUrl(s.getOauth());
            inMemoryConfigStorage.setAppid(s.getAppId());
            return inMemoryConfigStorage;
        }).collect(Collectors.toMap(k -> k.getAppId(), a -> a, (o, n) -> o));
    }

    /**
     * 获取微信公众号服务
     *
     * @return
     */
    public static Map<String, WxMpService> getWxMpServices() {
        return mpServices;
    }

    /**
     * 获取微信授权URL
     *
     * @return
     */
    public static Map<String, InMemoryConfigStorage> getConfig() {
        return config;
    }
}
