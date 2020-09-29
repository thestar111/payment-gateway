package com.bluetoop.payment.api.oauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <授权URL>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 4:07 下午
 * private String authUrl;
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@RequestMapping("/api/wechart/oauth")
public interface OAuthFacade {

    /**
     * 用户网页授权
     *
     * @param request   Servlet请求
     * @param response  Servlet返回请求
     * @param appid     公众号ID
     * @param returnUrl 跳转URL
     * @return
     */
    @GetMapping(value = "/{appid}")
    void redirect(HttpServletRequest request, HttpServletResponse response,
                  @PathVariable("appid") String appid,
                  @RequestParam("rl") String returnUrl);

    /**
     * 通过网页授权获取用户CODE, 去获取用户openid
     *
     * @param appid 微信公众号ID
     * @param code  微信请求CODE
     * @param state 微信回调URL
     * @return
     */
    @GetMapping(value = {"/code/{appid}", "/code/{appid}/{cid}"})
    void oauth(HttpServletRequest request, HttpServletResponse response,
               @PathVariable("appid") String appid,
               @RequestParam("code") String code,
               @RequestParam("state") String state);
}
