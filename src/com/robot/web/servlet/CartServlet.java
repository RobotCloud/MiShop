package com.robot.web.servlet;

import com.robot.pojo.Cart;
import com.robot.pojo.Goods;
import com.robot.pojo.User;
import com.robot.service.CartService;
import com.robot.service.GoodsService;
import com.robot.service.impl.CartServiceImpl;
import com.robot.service.impl.GoodsServiceImpl;
import com.robot.util.Message;
import com.robot.util.SysConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * 购物车处理。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
@WebServlet(name = "CartServlet", value = "/cartServlet")
public class CartServlet extends BaseServlet {
    CartService cartService = new CartServiceImpl();

    // 查询购物车
    public Message queryCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Message message = new Message();
        if (user != null) {
            List<Cart> carts = cartService.queryCart(user.getId());
            if (carts != null && carts.size() > 0) {
                message.setObject1(carts);
                message.setCode(200);
                message.setMessage("查询成功");
            } else {
                message.setCode(-1);
                message.setMessage("购物车空空如也，快去添加商品吧");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 更新商品数量
    public Message updateGoodsNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Message message = new Message();
        if (user != null) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int goodsId = Integer.parseInt(request.getParameter("goodsId"));
            int update = Integer.parseInt(request.getParameter("update"));
            Cart cart = cartService.updateGoodsNum(cartId, goodsId, update);
            // 如果数量为0，则不能再减
            if (cart == null) {
                message.setCode(-3);
                message.setMessage("就剩一件了，不能再减了！");
                return message;
            }
            // 计算当前用户的购物车总价格
            List<Cart> carts = cartService.queryCart(user.getId());
            BigDecimal totalMoney = new BigDecimal("0");
            for (Cart cart1 : carts) {
                totalMoney = totalMoney.add(cart1.getCount());
            }
            message.setCode(200);
            message.setMessage("修改成功");
            message.setObject1(cart);
            message.setObject2(totalMoney);
        } else {
            message.setCode(-2);
            message.setMessage("未登录");
        }
        return message;
    }

    // 将商品添加到购物车中
    public Message addGoodsToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        if (user != null) {
            int result = cartService.addGoodsToCart(user.getId(), goodsId);
            if (result > 0) {
                message.setCode(200);
                message.setMessage("添加成功");
            } else {
                message.setCode(-1);
                message.setMessage("添加失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("账号未登录");
        }
        return message;
    }

    // 删除购物车
    public Message deleteCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int result = cartService.deleteCartByCid(cartId);
        if (user != null) {
            if (result == 0) {
                message.setCode(-1);
                message.setMessage("删除失败");
            } else if (result > 0) {
                // 计算当前用户的购物车总价格
                List<Cart> carts = cartService.queryCart(user.getId());
                BigDecimal totalMoney = new BigDecimal("0");
                for (Cart cart1 : carts) {
                    totalMoney = totalMoney.add(cart1.getCount());
                }
                message.setCode(200);
                message.setMessage("删除成功");
                message.setObject1(totalMoney);
            } else { // 没有此ID的购物车
                message.setCode(-3);
                message.setMessage("没有此条购物车记录");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 清空购物车
    public Message clearCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int result = cartService.clearCartByUid(user.getId());
            if (result > 0) {
                message.setCode(200);
                message.setMessage("清空购物车成功");
            } else {
                message.setCode(-1);
                message.setMessage("清空购物车失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }
}
