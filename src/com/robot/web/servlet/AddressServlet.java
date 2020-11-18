package com.robot.web.servlet;

import com.robot.pojo.Address;
import com.robot.pojo.User;
import com.robot.service.AddressService;
import com.robot.service.impl.AddressServiceImpl;
import com.robot.util.Message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 收货地址。
 *
 * @author 张宝旭
 * @date 2020/9/18
 */
@WebServlet(value = "/addressServlet")
public class AddressServlet extends BaseServlet {
    AddressService addressService = new AddressServiceImpl();

    // 查询指定用户的所有地址
    public Message queryAllAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Address> addresses = addressService.queryAllAddress(user.getId());
            if (addresses != null && addresses.size() > 0) {
                message.setCode(200);
                message.setMessage("查询成功");
                message.setObject1(addresses);
            } else {
                message.setCode(-1);
                message.setMessage("收货地址列表为空");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 删除指定用户的地址
    public Message deleteAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        int aid = Integer.parseInt(request.getParameter("aid"));
        if (user != null) {
            int result = addressService.deleteAddress(aid, user.getId());
            if (result > 0) {
                message.setCode(200);
                message.setMessage("删除成功");
            } else if (result == -1) {
                message.setCode(0);
                message.setMessage("没有此地址");
            } else {
                message.setCode(-1);
                message.setMessage("删除失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 添加收货地址
    public Message addAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        if (user != null) {
            Address address = new Address(null, user.getId(), name, phone, detail, 0);
            int result = addressService.addAddress(address);
            if (result > 0) {
                message.setCode(200);
                message.setMessage("添加成功");
            } else {
                message.setCode(-1);
                message.setMessage("添加失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 设置默认地址
    public Message setFaultAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        int aid = Integer.parseInt(request.getParameter("aid"));
        if (user != null) {
            // 设置默认地址，返回原来默认地址的ID
            int result = addressService.setFaultAddress(user.getId(), aid);
            if (result > 0) {
                message.setCode(200);
                message.setMessage("设置成功");
                message.setObject1(result);
            } else {
                message.setCode(-1);
                message.setMessage("设置失败");
            }
        } else {
            message.setCode(-2);
            message.setMessage("用户未登录");
        }
        return message;
    }
}
