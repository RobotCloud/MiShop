package com.robot.dao.impl;

import com.robot.dao.OrderDao;
import com.robot.pojo.Address;
import com.robot.pojo.Order;
import com.robot.util.DbPoolUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单操作。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class OrderDaoImpl implements OrderDao {
    QueryRunner queryRunner = new QueryRunner();

    // 查询指定用户的所有订单
    @Override
    public List<Order> selectAllOrderByUid(int uid) throws Exception {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT o.o_id as id, o.u_id as uid, o.a_id as aid, o.o_count as 'count', o.o_time as 'time', o.o_state as state," +
                    " a.a_id as aid, a.a_name as name, a.a_phone as phone, a.a_detail as detail, a.a_state as state" +
                    " FROM orders as o" +
                    " INNER JOIN address as a" +
                    " ON o.a_id=a.a_id" +
                    " WHERE o.u_id=?";
            List<Order> orders = new ArrayList<>();
            List<Map<String, Object>> list = queryRunner.query(connection, sql, new MapListHandler(), uid);
            for (Map<String, Object> map : list) {
                Order order = new Order();
                Address address = new Address();
                BeanUtils.populate(order, map);
                BeanUtils.populate(address, map);
                order.setAddress(address);
                orders.add(order);
            }
            return orders;
        } finally {
            DbPoolUtils.close();
        }
    }

    // 添加新订单
    @Override
    public int insertOrder(Order order) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "INSERT INTO orders (o_id, u_id, a_id, o_count, o_time, o_state) VALUES(?,?,?,?,?,?)";
            Object[] params = {order.getId(), order.getUid(), order.getAid(), order.getCount(), order.getTime(), order.getState()};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据订单ID，查询订单
    @Override
    public Order selectOrderById(String id) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT o_id as id, u_id as uid, a_id as aid, o_count as count, o_time as time, o_state as state FROM orders WHERE o_id=?";
            return queryRunner.query(connection, sql, new BeanHandler<>(Order.class), id);
        } finally {
            DbPoolUtils.close();
        }
    }
}
