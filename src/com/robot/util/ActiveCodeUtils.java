package com.robot.util;

import java.util.UUID;

/**
 * 生成激活码。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class ActiveCodeUtils {

    /**
     * 生成激活码。
     *
     * @return 激活码，只包含数字和字母
     */
    public static String getActiveCode() {
        return String.valueOf(UUID.randomUUID()).replace("-", "");
    }
}
