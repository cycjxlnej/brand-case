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
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、调用service查询
        List<Brand> brands = brandService.selectAll();

        // 2、转为json
        String jsonString = JSON.toJSONString(brands);

        // 3、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
