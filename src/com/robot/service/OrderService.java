package com.robot.service;

import com.robot.pojo.Cart;
import com.robot.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品订单管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public interface OrderService {

    /**
     * 查询当前订单预览。
     *
     * @param uid 用户ID
     * @return 订单列表
     */
    List<Cart> queryCurrentOrderList(int uid) throws Exception;

    /**
     * 查询指定用户的所有订单。
     *
     * @param uid 用户ID
     * @return 指定用户的所有订单
     */
    List<Order> queryAllOrderByUid(int uid) throws Exception;

    /**
     * 添加订单
     *
     * @param order 新订单
     * @return 添加成功的数量
     */
    int addOrder(Order order) throws SQLException;

    /**
     * 根据订单ID，查询订单
     *
     * @param id 订单ID
     * @return 订单
     */
    Order queryOrderById(String id) throws SQLException;
}
