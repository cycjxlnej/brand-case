package com.cycjxlnej.web.servlet.old; /**
 * @Author chenwei
 * @Date 2023/4/20 10:13 上午
 */

import com.alibaba.fastjson.JSON;
import com.cycjxlnej.pojo.Brand;
import com.cycjxlnej.service.BrandService;
import com.cycjxlnej.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine(); // json字符串

        // 2、转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 3、调用service添加
        brandService.add(brand);

        // 4、响应成功的标识

        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
