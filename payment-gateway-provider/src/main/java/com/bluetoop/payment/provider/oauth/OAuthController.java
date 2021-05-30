package com.bluetoop.payment.provider.oauth;

import com.bluetoop.payment.api.oauth.OAuthFacade;
import com.bluetoop.payment.core.config.WxMpConfig;
import com.bluetoop.payment.core.cons.type.PrintLog;
import com.bluetoop.payment.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

import static com.bluetoop.payment.core.cons.type.OAuthType.SNS_BASE_INFO;

/**
 * <微信授权网页服务>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/27 4:08 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class OAuthController implements OAuthFacade {

    /**
     * 用户网页授权
     *
     * @param request   Servlet请求
     * @param response  Servlet返回请求
     * @param appid     公众号ID
     * @param returnUrl 跳转URL
     * @return
     */
    @PrintLog
    public void redirect(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable("appid") String appid,
                         @RequestParam("rl") String returnUrl) {
        final WxMpService wxMpService = WxMpConfig.getWxMpServices().get(appid);
        String oauth = WxMpConfig.getConfig().get(appid).getAuthorizationCodeUrl();
        if (null == wxMpService) {
            throw new IllegalArgumentException(String.format("The appid : [%d] is Invalid.", appid));
        }
        if (StringUtil.isEmpty(oauth)) {
            throw new IllegalArgumentException(String.format("The oauth : [%d] is Invalid.", appid));
        }
        String code_url = String.format(oauth, appid);
        String redirect = wxMpService.oauth2buildAuthorizationUrl(code_url, SNS_BASE_INFO.getType(), URLEncoder.encode(returnUrl));
        log.debug("【OAuthController】oauth link：{}", redirect);
        try {
            response.sendRedirect(redirect);
        } catch (IOException e) {
            log.error("【OAuthController】oauth redirect error.", e);
            return;
        }
    }

    /**
     * 通过网页授权获取用户CODE, 去获取用户openid
     *
     * @param appid 微信公众号ID
     * @param code  微信请求CODE
     * @param state 微信回调URL
     * @return
     */
    @PrintLog
    public void oauth(HttpServletRequest request, HttpServletResponse response,
                      @PathVariable("appid") String appid,
                      @RequestParam("code") String code,
                      @RequestParam("state") String state) {
        if (StringUtils.isEmpty(code)) {
            return;
        }
        final WxMpService wxMpService = WxMpConfig.getWxMpServices().get(appid);
        log.debug("【OAuthController】oauth page the code ============================>>>> {}", code);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【OAuthController】oauth page failed. ========================>>>> {}", e.getError());
            return;
        }
        if (null == wxMpOAuth2AccessToken) {
            log.debug("【OAuthController】oauth page failed. OAuth2Token : ==================>>>> {}", wxMpOAuth2AccessToken);
            return;
        }
        log.debug("【OAuthController】oauth page success. The OAuth2Token : =================>>>> {}", wxMpOAuth2AccessToken);
        //获取用户OPENID
        String openid = wxMpOAuth2AccessToken.getOpenId();
        Cookie _oid = new Cookie("_oid", openid);
        //_oid.setHttpOnly(true);
        _oid.setPath("/");
        _oid.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(_oid);
        log.debug("【OAuthController】 oauth page success. The User openid : =================>>>> {}", openid);
        try {
            response.sendRedirect(state);
        } catch (IOException e) {
            log.error("【OAuthController】 oauth page failed.", e);
            return;
        }
    }
}
