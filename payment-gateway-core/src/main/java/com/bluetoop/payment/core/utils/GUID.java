package com.bluetoop.payment.core.utils;

import java.util.UUID;

/**
 * <UID生成>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/28 11:32 上午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
public final class GUID {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String get() {
        UUID uuid = UUID.randomUUID();
        StringBuffer sb = new StringBuffer();
        get(sb, uuid.getMostSignificantBits());
        get(sb, uuid.getLeastSignificantBits());
        return sb.toString();
    }

    /**
     * 压缩UUID编码
     *
     * @param sb
     * @param bits
     * @return
     */
    private static StringBuffer get(StringBuffer sb, long bits) {
        for (int var3 = 13; var3-- > 0; bits >>>= 5) {
            long low = bits & 31L;
            if (low < 10L) {
                sb.append((char) ((int) (48L + low)));
            } else {
                sb.append((char) ((int) (65L + (low - 10L))));
            }
        }

        return sb;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            System.out.println(get());
        }
    }
}
