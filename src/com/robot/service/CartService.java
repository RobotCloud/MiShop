package com.robot.service;

import com.robot.pojo.Cart;

import java.sql.SQLException;
import java.util.List;

/**
 * 购物车的业务操作。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public interface CartService {
    /**
     * 查询某个用户的购物车。
     *
     * @param uid 用户ID
     * @return 购物车
     */
    List<Cart> queryCart(int uid) throws SQLException, Exception;

    /**
     * 根据购物车ID和商品ID减少购物车中的指定商品数量。
     *
     * @param cartId  购物车ID
     * @param goodsId 商品ID
     * @param update  修改商品的数量
     * @return 返回修改成功后的购物车栏
     */
    Cart updateGoodsNum(int cartId, int goodsId, int update) throws SQLException;

    /**
     * 根据用户ID和商品ID，将指定商品添加到购物车中。
     *
     * @param uid     用户ID
     * @param goodsId 商品ID
     * @return 添加成功的数量
     */
    int addGoodsToCart(int uid, int goodsId) throws SQLException;

    /**
     * 根据购物车ID，删除购物车
     *
     * @param cid 购物车ID
     * @return 删除成功的数量
     */
    int deleteCartByCid(int cid) throws SQLException;

    /**
     * 清空指定用户的购物车。
     *
     * @param uid 用户ID
     * @return 删除成功的数量
     */
    int clearCartByUid(int uid) throws SQLException;
}
