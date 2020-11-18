package com.robot.service.impl;

import com.robot.dao.CartDao;
import com.robot.dao.GoodsDao;
import com.robot.dao.impl.CartDaoImpl;
import com.robot.dao.impl.GoodsDaoImpl;
import com.robot.pojo.Cart;
import com.robot.pojo.Goods;
import com.robot.service.CartService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * 购物车管理。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class CartServiceImpl implements CartService {
    CartDao cartDao = new CartDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();

    // 查询购物车
    @Override
    public List<Cart> queryCart(int uid) throws SQLException, Exception {
        return cartDao.selectCart(uid);
    }

    // 更新商品数量
    @Override
    public Cart updateGoodsNum(int cartId, int goodsId, int update) throws SQLException {
        // 1 根据商品ID，找到那个购物车栏中的商品
        Goods goods = goodsDao.selectGoodsById(goodsId);
        // 2 根据购物车ID，修改购物车信息（将修改后的商品添加到购物车）
        Cart cart = cartDao.selectCartByCid(cartId);
        // 如果数量为1，则不能再减
        if (cart.getNum() <= 1 && update < 0) {
            return null;
        }
        cart.setNum(cart.getNum() + update);
        cart.setCount(goods.getPrice().multiply(BigDecimal.valueOf(cart.getNum())));
        cartDao.updateCart(cart);
        return cart;
    }

    // 添加商品到购物车
    @Override
    public int addGoodsToCart(int uid, int goodsId) throws SQLException {
        // 商品本来就在，根据商品ID，查询出此商品
        Goods goods = goodsDao.selectGoodsById(goodsId);

        // 查询商品是否已经添加到购物车中
        int cartId = cartDao.selectGoodsFromCart(uid, goodsId);
        // 如果购物车中有此商品，则将数量加一，如果没有，则添加到购物车
        if (cartId != 0) {
            // 存在，将购物车中此商品的的数量加一
            updateGoodsNum(cartId, goodsId, 1);
            return 1;
        } else {
            // 不存在，将此商品添加到购物车
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setGid(goodsId);
            cart.setNum(1);
            cart.setCount(goods.getPrice().multiply(BigDecimal.valueOf(cart.getNum())));
            cart.setGoods(goods);
            return cartDao.insertGoodsToCart(cart);
        }
    }

    // 根据ID删除购物车
    @Override
    public int deleteCartByCid(int cid) throws SQLException {
        Cart cart = cartDao.selectCartByCid(cid);
        if (cart == null) {
            return -1;
        }
        return cartDao.deleteCartByCid(cid);
    }

    // 清空指定用户的购物车
    @Override
    public int clearCartByUid(int uid) throws SQLException {
        return cartDao.deleteCartByUid(uid);
    }

}
