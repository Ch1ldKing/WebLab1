package com.example.weblab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import static java.lang.System.console;
import static java.lang.System.out;

@WebServlet(value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Read from request
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        out.println(buffer.toString());
        // 将 buffer 转为 json 对象
        JSONObject data = new JSONObject(buffer.toString());
//        out.println(data);
        String username = data.getString("username");
        String password = data.getString("password");

        // Dummy validation logic
        boolean isValidUser = validateUser(username, password);

        JSONObject jsonResponse = new JSONObject();
        if (isValidUser) {
            jsonResponse.put("success", true);

            // If validation success
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // You might redirect or manage sessions here as per your logic
        } else {
            jsonResponse.put("success", false);
            // In case of failure, you might want to add more info
        }

        // Output response
        response.getWriter().write(jsonResponse.toString());
    }

    // A sample user validation method
    private boolean validateUser(String username, String password) {
        // This is just a dummy validation. Replace it with your actual user validation logic.
        return "admin".equals(username) && "123".equals(password);
    }


    // 一个示例的用户验证方法，实际中应该查询数据库或其他用户存储机制

}
