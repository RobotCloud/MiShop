package com.robot.util;

import java.util.Base64;

/**
 * 数据加密解密。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class Base64Utils {

    //加密
    public static String encode(String msg) {
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }

    //解密
    public static String decode(String msg) {
        return new String(Base64.getDecoder().decode(msg));
    }

}
