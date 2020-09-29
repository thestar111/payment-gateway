/**
 * 文 件 名:  StringUtil
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  14:48
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * <字符串工具类>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/19 14:48
 * @see [相关类/方法]
 * @since JDK 1.8
 */
public final class StringUtil extends StringUtils {

    /**
     * 随机字符串（0-9）
     */
    private static final String INT_TEMP = "0123456789";

    /**
     * 随机字符串（a-ZA-Z）
     */
    private static final String STR_TEMP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 所有字符串
     */
    private static final String ALL_TEMP = INT_TEMP + STR_TEMP;

    private static final Random RANDOM = new Random();

    /**
     * 随机数生成
     *
     * @param count
     * @return {String}
     */
    public static String random(int count, RandomType randomType) {
        if (count == 0) {
            return "";
        }
        if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            if (RandomType.INT.equals(randomType)) {
                buffer[i] = INT_TEMP.charAt(RANDOM.nextInt(INT_TEMP.length()));
            } else if (RandomType.STRING.equals(randomType)) {
                buffer[i] = STR_TEMP.charAt(RANDOM.nextInt(STR_TEMP.length()));
            } else {
                buffer[i] = ALL_TEMP.charAt(RANDOM.nextInt(ALL_TEMP.length()));
            }
        }
        return new String(buffer);
    }

    /**
     * 生成的随机数类型
     */
    public enum RandomType {
        /**
         * 整数
         */
        INT,
        /**
         * 字符串
         */
        STRING,
        /**
         * 所有类型
         */
        ALL;
    }
}


