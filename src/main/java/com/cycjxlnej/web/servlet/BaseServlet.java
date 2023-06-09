package com.cycjxlnej.web.servlet;

/**
 * @Author chenwei
 * @Date 2023/4/20 1:52 下午
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet,根据请求的最后一段路径来进行方法分发
 */
public class BaseServlet extends HttpServlet {
    // 根据请求的最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求路径
        String uri = req.getRequestURI(); // uri: /brand-case/brand/selectAll
//        System.out.println(uri);
        // 2、获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
//        System.out.println(methodName);

        // 3、执行方法
        // 3.1 获取BrandServlet / UserServlet 字节码对象 Class
//        System.out.println(this);
        Class<? extends BaseServlet> cls = this.getClass();

        // 3.2 获取方法 Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
