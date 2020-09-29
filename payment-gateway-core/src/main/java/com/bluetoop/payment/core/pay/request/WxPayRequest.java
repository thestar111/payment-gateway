package com.bluetoop.payment.core.pay.request;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <微信支付请求实体>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/19 14:29
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Getter
@Setter
@XStreamAlias("xml")
public class WxPayRequest extends WxPayUnifiedOrderRequest {

    /**
     * 将建构的 builder 转为 Map
     *
     * @return 转化后的 Map
     */
    public Map<String, Object> toMap() {
        String[] fieldNames = getFiledNames(this);
        HashMap<String, Object> map = new HashMap<>(fieldNames.length);
        for (int i = 0, len = fieldNames.length; i < len; i++) {
            String name = fieldNames[i];
            Object value = getFieldValueByName(name, this);
            if (null != value) {
                map.put(name, value);
            }
        }
        return map;
    }

    /**
     * 获取属性名数组
     *
     * @param obj 对象
     * @return 返回对象属性名数组
     */
    public String[] getFiledNames(Object obj) {
        Field[] fields = obj.getClass().getSuperclass().getDeclaredFields();
        Field[] superFields = obj.getClass().getSuperclass().getSuperclass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0, len = fieldNames.length; i < len; i++) {
            fieldNames[i] = fields[i].getName();
        }

        String[] superFieldNames = new String[superFields.length];
        for (int i = 0, len = superFieldNames.length; i < len; i++) {
            superFieldNames[i] = superFields[i].getName();
        }
        return ArrayUtils.addAll(fieldNames, superFieldNames);
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName 属性名称
     * @param obj       对象
     * @return 返回对应属性的值
     */
    public Object getFieldValueByName(String fieldName, Object obj) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = new StringBuffer().append("get").append(firstLetter).append(fieldName.substring(1)).toString();
            Method method = obj.getClass().getMethod(getter, new Class[]{});
            return method.invoke(obj, new Object[]{});
        } catch (Exception e) {
            return null;
        }
    }
}

