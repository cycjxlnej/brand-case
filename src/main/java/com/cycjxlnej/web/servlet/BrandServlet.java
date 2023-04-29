package com.cycjxlnej.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cycjxlnej.pojo.Brand;
import com.cycjxlnej.pojo.PageBean;
import com.cycjxlnej.service.BrandService;
import com.cycjxlnej.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @Author chenwei
 * @Date 2023/4/20 1:55 下午
 */

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("brand selectAll...");

        // 1、调用service查询
        List<Brand> brands = brandService.selectAll();

        // 2、转为json
        String jsonString = JSON.toJSONString(brands);

        // 3、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);


    }

    /**
     * 添加数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("brand add...");

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

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("brand deleteByIds...");

        // 1、接收数据 [1, 2, 3]
        BufferedReader br = request.getReader();
        String params = br.readLine(); // json字符串

        // 2、转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        // 3、调用service添加
        brandService.deleteByIds(ids);

        // 4、响应成功的标识

        response.getWriter().write("success");
    }


    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("brand selectByPage...");

        // 1、接收数据 当前页码和每页展示条数 url?currentPage=1&pageSize=5
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        int currentPage1 = Integer.parseInt(currentPage);
        int pageSize1 = Integer.parseInt(pageSize);


        // 2、调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage1, pageSize1);

        // 3、转为json
        String jsonString = JSON.toJSONString(pageBean);

        // 4、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);


    }


    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("brand selectByPageAndCondition...");


        // 1、接收数据 当前页码和每页展示条数 url?currentPage=1&pageSize=5
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        int currentPage1 = Integer.parseInt(currentPage);
        int pageSize1 = Integer.parseInt(pageSize);
        System.out.println(currentPage1);
        System.out.println(pageSize1);

        // 获取查询条件对象

        BufferedReader br = request.getReader();
        String params = br.readLine(); // json字符串

        System.out.println(params);

        // 转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);


        // 2、调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage1, pageSize1, brand);

        // 3、转为json
        String jsonString = JSON.toJSONString(pageBean);

        // 4、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);


    }

}
