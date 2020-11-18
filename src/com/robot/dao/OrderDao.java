package com.robot.dao;

import com.robot.pojo.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * 订单管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public interface OrderDao {
    /**
     * 查询指定用户的所有订单。
     *
     * @param uid 用户ID
     * @return 指定用户的所有订单
     */
    List<Order> selectAllOrderByUid(int uid) throws Exception;

    /**
     * 添加订单。
     *
     * @param order 新订单
     * @return 添加成功的数量
     */
    int insertOrder(Order order) throws SQLException;

    /**
     * 根据订单ID，查询订单
     * @param id 订单ID
     * @return 订单
     */
    Order selectOrderById(String id) throws SQLException;
}
