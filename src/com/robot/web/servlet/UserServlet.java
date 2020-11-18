package com.robot.web.servlet;

import cn.dsna.util.images.ValidateCode;
import com.robot.pojo.User;
import com.robot.service.UserService;
import com.robot.service.impl.UserServiceImpl;
import com.robot.util.ActiveCodeUtils;
import com.robot.util.Base64Utils;
import com.robot.util.Message;
import com.robot.util.SysConstant;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户处理。
 *
 * @author 张宝旭
 * @date 2020/9/14
 */
@WebServlet(name = "UserServlet", value = "/userServlet")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    // 登录
    public Message login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Message message = new Message();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 检查验证码
        String validateCode1 = request.getParameter("validateCode1");
        String validateCode2 = (String) request.getSession().getAttribute("validateCode");
        if (!validateCode1.equalsIgnoreCase(validateCode2)) {
            message.setCode(0);
            message.setMessage("验证码错误");
            return message;
        }
        // 检查用户
        User user = userService.queryUser(username, password);
        if (user != null) {
            if (user.getFlag() == 1) {
                request.getSession().setAttribute("user", user);
                message.setCode(200);
                message.setMessage("登录成功");
                message.setObject1(user);
            } else {
                message.setCode(-1);
                message.setMessage("账号未激活");
            }
        } else {
            message.setCode(-1);
            message.setMessage("登录失败，用户名或密码错误，再试一次");
        }
        return message;
    }

    // 注销
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        try {
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 检查是否登录
    public Message checkLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Message message = new Message();
        if (user != null) {
            message.setCode(200);
            message.setMessage("用户已登录");
            message.setObject1(user);
        } else {
            message.setCode(-1);
            message.setMessage("用户未登录");
        }
        return message;
    }

    // 检测用户名是否可用
    public Message checkUserName(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String username = request.getParameter("username");
        boolean userExist = userService.queryUserByUsername(username);
        Message message = new Message();
        if (userExist) {
            message.setCode(200);
            message.setMessage("用户名名已存在");
        } else {
            message.setCode(-1);
            message.setMessage("用户名可用");
        }
        return message;
    }

    // 查询所有用户
    public Message queryAllUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<User> userList = userService.queryAllUser();
        Message message = new Message();
        if (userList != null && userList.size() != 0) {
            message.setCode(200);
            message.setMessage("查询用户信息成功");
        } else {
            message.setCode(-1);
            message.setMessage("用户列表为空");
        }
        return message;
    }

    // 注册
    public Message register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        Message message = new Message();
        // 用户信息
        BeanUtils.populate(user, request.getParameterMap());
        user.setCode(ActiveCodeUtils.getActiveCode());
        user.setFlag(SysConstant.NOT_ACTIVE);
        user.setRole(SysConstant.CUSTOMER);

        int result = userService.addUser(user);
        if (result > 0) {
            message.setCode(200);
            message.setMessage("注册成功");
            String baseCode = Base64Utils.encode(user.getCode());
            message.setObject1(baseCode);
        } else {
            message.setCode(-1);
            message.setMessage("注册失败");
        }
        return message;
    }

    // 获取验证码
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 创建验证码
        ValidateCode code = new ValidateCode(100, 40, 4, 5);
        // 存储到session
        request.getSession().setAttribute("validateCode", code.getCode());
        // 响应到页面
        ImageIO.write(code.getBuffImg(), "jpg", response.getOutputStream());
    }

    // 激活账户
    public void activeAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取加密后的激活码
        String baseCode = request.getParameter("baseCode");//编码过
        // 解码
        String code = Base64Utils.decode(baseCode);
        int result = userService.activeAccount(code);
        if (result == 1) {
            response.sendRedirect(request.getContextPath() + "/message.html?code=" + 200);
        } else if (result == 2) {
            response.sendRedirect(request.getContextPath() + "/message.html?code=" + (-2));
        } else {
            response.sendRedirect(request.getContextPath() + "/message.html?code=" + (-1));
        }
    }

}
