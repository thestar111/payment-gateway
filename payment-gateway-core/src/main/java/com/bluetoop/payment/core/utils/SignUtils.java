/**
 * 文 件 名:  CommonUtils
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  14:57
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.bluetoop.payment.core.cons.type.SignType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * <签名工具类>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/19 14:57
 * @see [相关类/方法]
 * @since JDK 1.8
 */
public final class SignUtils {

    /**
     * 生成签名
     *
     * @param params     需要签名的参数
     * @param partnerKey 密钥
     * @param signType   签名类型
     * @return 签名后的数据
     */
    public static String createSign(Map<String, Object> params, String partnerKey, SignType signType) {
        if (null == signType) {
            signType = SignType.MD5;
        }
        // 生成签名前先去除sign
        String tempStr = packageSign(params, false);
        String stringSignTemp = tempStr + "&key=" + partnerKey;
        if (signType == SignType.MD5) {
            return md5(stringSignTemp).toUpperCase();
        } else {
            return hmacSha256(stringSignTemp, partnerKey).toUpperCase();
        }
    }

    /**
     * 组装签名的字段
     *
     * @param params     参数
     * @param urlEncoder 是否urlEncoder
     * @return {String}
     */
    public static String packageSign(Map<String, Object> params, boolean urlEncoder) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, Object> sortedParams = new TreeMap<>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Object> param : sortedParams.entrySet()) {
            String value = param.getValue() + "";
            if (StrUtil.isEmpty(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            if (urlEncoder) {
                try {
                    value = urlEncode(value);
                } catch (UnsupportedEncodingException e) {
                }
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * SHA加密
     *
     * @param data
     * @param key
     * @return
     */
    public static String hmacSha256(String data, String key) {
        return SecureUtil.hmac(HmacAlgorithm.HmacSHA256, key).digestHex(data, CharsetUtil.UTF_8);
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String md5(String data) {
        return SecureUtil.md5(data);
    }

    /**
     * URL编码
     *
     * @param src
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, CharsetUtil.UTF_8).replace("+", "%20");
    }
}
