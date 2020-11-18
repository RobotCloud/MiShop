package com.robot.web.servlet;

import com.google.gson.Gson;
import com.robot.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Servlet基类，所有请求Servlet操作都将由此来处理。
 *
 * @author 张宝旭
 * @date 2020/9/15
 */
@WebServlet(name = "BaseServlet", value = "/baseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        // 如果方法名为空，则跳转到首页
        if (methodName == null) {
            response.sendRedirect(request.getContextPath() + "/index.html");
        }
        response.setContentType("application/json;charset=utf-8");
        try {
            // 获取请求的Servlet类，this为类对象
            Class clazz = this.getClass();
            // 获取调用的具体方法
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 调用方法，获取执行结果
            Object result = method.invoke(this, request, response);
            if (result != null) {
                // 返回JSON数据
                String jsonResult = new Gson().toJson(result);
                response.getWriter().print(jsonResult);
            } // 否则什么都不返回
        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.setCode(-1);
            message.setMessage("服务器错误");
            String jsonResult = new Gson().toJson(message);
            response.getWriter().print(jsonResult);
        }
    }
}
