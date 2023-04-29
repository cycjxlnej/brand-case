package com.cycjxlnej.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author chenwei
 * @Date 2023/4/20 1:55 下午
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user selectAll...");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user add...");
    }
}
