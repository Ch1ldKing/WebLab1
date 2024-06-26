package com.example.weblab1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 设置响应类型
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 使用getParameter方法接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 进行用户验证
        boolean isValidUser = "admin".equals(username) && "123".equals(password);

        try (PrintWriter out = response.getWriter()) {
            if (isValidUser) {
                // 认证成功
                out.print("{\"success\": true, \"message\": \"Login successful\"}");
            } else {
                // 认证失败
                out.print("{\"success\": false, \"message\": \"Invalid username or password\"}");
            }
        }
    }
}
