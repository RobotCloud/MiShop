package com.robot.util;

/**
 * 生成订单ID。
 *
 * @author 张宝旭
 * @date 2020/9/18
 */
public class OrderIdUtils {

    /**
     * 根据用户ID，生成订单ID
     *
     * @param uid 用户ID
     * @return 订单ID
     */
    public static String createOrderId(int uid) {
        long time = System.currentTimeMillis();
        return time + "" + uid;
    }
}
