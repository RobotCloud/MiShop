package com.robot.dao.impl;

import com.robot.dao.CartDao;
import com.robot.pojo.Cart;
import com.robot.pojo.Goods;
import com.robot.util.DbPoolUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 操作购物车。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class CartDaoImpl implements CartDao {
    QueryRunner queryRunner = new QueryRunner();

    // 查询购物车
    @Override
    public List<Cart> selectCart(int uid) throws Exception {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT c.c_id as cid, c.g_id as id, c.c_count as count, c.c_num as num," +
                    "g.g_name as name, g.g_time as time, g.g_image as image, g.g_price as price, g.g_info as info " +
                    "FROM cart c " +
                    "INNER JOIN goods g " +
                    "ON c.g_id = g.g_id " +
                    "WHERE c.u_id = ?";
            List<Cart> carts = new ArrayList<>();
            List<Map<String, Object>> list = queryRunner.query(connection, sql, new MapListHandler(), uid);
            for (Map<String, Object> map : list) {
                Cart cart = new Cart();
                Goods goods = new Goods();
                BeanUtils.populate(cart, map);
                BeanUtils.populate(goods, map);
                cart.setGoods(goods);
                carts.add(cart);
            }
            return carts;
        } finally {
            DbPoolUtils.close();
        }
    }

    // 查询指定商品所在的购物车
    @Override
    public int selectGoodsFromCart(int uid, int gid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT c_id as cid FROM cart WHERE u_id=? AND g_id=?";
            Cart cart = queryRunner.query(connection, sql, new BeanHandler<>(Cart.class), uid, gid);
            if (cart != null) {
                return cart.getCid();
            } else {
                return 0;
            }
        } finally {
            DbPoolUtils.close();
        }
    }

    // 更新购物车中指定商品的数量和总价
    @Override
    public int updateCart(Cart cart) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "UPDATE cart SET c_count=?, c_num=? WHERE c_id=?";
            Object[] params = {cart.getCount(), cart.getNum(), cart.getCid()};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据购物车ID，删除指定购物车栏
    @Override
    public int deleteCartByCid(int cid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "DELETE FROM cart WHERE c_id=?";
            return queryRunner.update(connection, sql, cid);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 清空指定用户的购物车
    @Override
    public int deleteCartByUid(int uid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "DELETE FROM cart WHERE u_id=?";
            return queryRunner.update(connection, sql, uid);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据购物车ID，查询指定购物车
    @Override
    public Cart selectCartByCid(int cid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT c_id as cid, u_id as uid, g_id as gid, c_count as count, c_num as num FROM cart WHERE c_id=?";
            return queryRunner.query(connection, sql, new BeanHandler<>(Cart.class), cid);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 添加一条购物车
    @Override
    public int insertGoodsToCart(Cart cart) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "INSERT INTO cart (c_id, u_id, g_id, c_count, c_num) VALUES(?,?,?,?,?)";
            Object[] params = {cart.getCid(), cart.getUid(), cart.getGid(), cart.getCount(), cart.getNum()};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }
}
