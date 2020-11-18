package com.robot.web.servlet;

import com.robot.pojo.Address;
import com.robot.pojo.Cart;
import com.robot.pojo.Order;
import com.robot.pojo.User;
import com.robot.service.AddressService;
import com.robot.service.CartService;
import com.robot.service.OrderService;
import com.robot.service.impl.AddressServiceImpl;
import com.robot.service.impl.CartServiceImpl;
import com.robot.service.impl.OrderServiceImpl;
import com.robot.util.DbPoolUtils;
import com.robot.util.Message;
import com.robot.util.OrderIdUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 订单处理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
@WebServlet(value = "/orderServlet")
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();
    AddressService addressService = new AddressServiceImpl();
    CartService cartService = new CartServiceImpl();

    // 查询当前订单预览
    public Message queryCurrentOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Cart> carts = orderService.queryCurrentOrderList(user.getId());
            if (carts != null && carts.size() > 0) {
                List<Address> addresses = addressService.queryAllAddress(user.getId());
                message.setCode(200);
                message.setMessage("查询成功");
                message.setObject1(carts);
                message.setObject2(addresses);
            } else {
                message.setCode(-1);
                message.setMessage("订单预览为空");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 查询指定用户的所有订单
    public Message queryAllOrderByUid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Order> orders = orderService.queryAllOrderByUid(user.getId());
            if (orders != null && orders.size() > 0) {
                List<Cart> carts = orderService.queryCurrentOrderList(user.getId());
                message.setCode(200);
                message.setMessage("查询成功");
                message.setObject1(orders);
            } else {
                message.setCode(-1);
                message.setMessage("查询失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 生成订单并返回
    public Message addAndQueryOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        String aid = request.getParameter("aid");
        // 如果收货地址为空，则直接返回
        if (aid.equals("undefined")) {
            message.setCode(-3);
            message.setMessage("请添加收货地址");
            return message;
        }
        if (user != null) {
            // 获取购物车中所有商品，求出总价格
            List<Cart> carts = orderService.queryCurrentOrderList(user.getId());
            BigDecimal totalMoney = new BigDecimal("0");
            for (Cart cart : carts) {
                totalMoney = totalMoney.add(cart.getCount());
            }
            int aId = Integer.parseInt(aid);
            // 获取选中的地址
            Address address = addressService.queryAddressByAid(aId);
            // 创建订单
            Order order = new Order();
            order.setId(OrderIdUtils.createOrderId(user.getId()));
            order.setUid(user.getId());
            order.setAid(aId);
            order.setCount(totalMoney);
            order.setTime(new Date());
            order.setState(0);
            // 开启事务
            DbPoolUtils.openTransaction();
            try {
                int result = orderService.addOrder(order);
                if (result > 0) {
                    // 清空购物车
                    int clear = cartService.clearCartByUid(user.getId());
                    if (clear > 0) {
                        DbPoolUtils.commit();
                        message.setCode(200);
                        message.setMessage("创建订单成功");
                        message.setObject1(order);
                    } else {
                        message.setCode(-1);
                        message.setMessage("创建订单失败");
                        DbPoolUtils.rollback();
                    }
                } else {
                    message.setCode(-1);
                    message.setMessage("创建订单失败");
                    DbPoolUtils.commit(); // 创建失败无所谓提交
                }
            } catch (Exception e) {
                e.printStackTrace();
                message.setCode(-1);
                message.setMessage("创建订单失败");
                DbPoolUtils.rollback();
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 修改用户订单
    public Message updateOrder(HttpServletRequest request, HttpServletResponse response) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        return null;
    }

    // 根据订单ID查询指定订单
    public Message queryOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String orderId = request.getParameter("orderId");
            Order order = orderService.queryOrderById(orderId);
            if (order != null) {
                message.setCode(200);
                message.setMessage("查询成功");
                message.setObject1(order);
            } else {
                message.setCode(-1);
                message.setMessage("查询失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }
}
