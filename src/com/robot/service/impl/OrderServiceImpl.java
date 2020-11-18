package com.robot.service.impl;

import com.robot.dao.CartDao;
import com.robot.dao.OrderDao;
import com.robot.dao.impl.CartDaoImpl;
import com.robot.dao.impl.OrderDaoImpl;
import com.robot.pojo.Cart;
import com.robot.pojo.Order;
import com.robot.service.OrderService;

import java.sql.SQLException;
import java.util.List;

/**
 * 订单管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class OrderServiceImpl implements OrderService {
    CartDao cartDao = new CartDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();

    // 查询当前订单预览
    @Override
    public List<Cart> queryCurrentOrderList(int uid) throws Exception {
        return cartDao.selectCart(uid);
    }

    // 查询指定用户的所有订单
    @Override
    public List<Order> queryAllOrderByUid(int uid) throws Exception {
        return orderDao.selectAllOrderByUid(uid);
    }

    // 添加订单
    @Override
    public int addOrder(Order order) throws SQLException {
        return orderDao.insertOrder(order);
    }

    // 根据订单ID，查询订单
    @Override
    public Order queryOrderById(String id) throws SQLException {
        return orderDao.selectOrderById(id);
    }
}
