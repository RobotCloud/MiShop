package com.robot.web.servlet;

import cn.dsna.util.images.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录验证码。
 *
 * @author 张宝旭
 * @date 2020/9/19
 */
@WebServlet(name = "CodeServlet", value = "/codeServlet")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建验证码
        ValidateCode code = new ValidateCode(100, 40, 4, 5);
        // 存储到session
        request.getSession().setAttribute("validateCode", code.getCode());
        // 响应到页面
        ImageIO.write(code.getBuffImg(), "jpg", response.getOutputStream());
    }
}
