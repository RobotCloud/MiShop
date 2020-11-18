package com.robot.dao;

import com.robot.pojo.Cart;

import java.sql.SQLException;
import java.util.List;

/**
 * 操作购物车.
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public interface CartDao {

    /**
     * 查询某个用户的购物车。
     *
     * @param uid 用户ID
     * @return 购物车
     */
    List<Cart> selectCart(int uid) throws SQLException, Exception;

    /**
     * 查询指定商品，是否存在于购物车中
     * @param uid 用户ID
     * @param gid 商品ID
     * @return 如果商品在购物车中，则返回购物车ID；如果不存在，则返回0
     */
    int selectGoodsFromCart(int uid, int gid) throws SQLException;


    /**
     * 修改购物车信息。
     *
     * @param cart 修改后的一栏购物车信息
     * @return 修改成功的数量
     */
    int updateCart(Cart cart) throws SQLException;


    /**
     * 根据购物车ID，删除购物车
     * @param cid 购物车ID
     * @return 删除成功的数量
     */
    int deleteCartByCid(int cid) throws SQLException;

    /**
     * 清空购物车，根据用户ID，删除指定用户的所有购物车。
     *
     * @param uid 用户ID
     * @return 删除成功的数量
     */
    int deleteCartByUid(int uid) throws SQLException;

    /**
     * 根据ID查询某条购物车栏。
     *
     * @param id 购物车栏ID
     * @return 购物车栏
     */
    Cart selectCartByCid(int id) throws SQLException;

    /**
     * 添加一条购物车。
     *
     * @param cart 添加的购物车
     * @return 添加成功的数量
     */
    int insertGoodsToCart(Cart cart) throws SQLException;
}
