package com.cycjxlnej.service.impl;

import com.cycjxlnej.mapper.BrandMapper;
import com.cycjxlnej.pojo.Brand;
import com.cycjxlnej.pojo.PageBean;
import com.cycjxlnej.service.BrandService;
import com.cycjxlnej.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author chenwei
 * @Date 2023/4/20 10:12 上午
 */
public class BrandServiceImpl implements BrandService {
    // 1、创建SqlSessionFactory工厂对象
    SqlSessionFactory sessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        // 2、获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4、调用方法
        List<Brand> brands = brandMapper.selectAll();

        // 5、释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        // 2、获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4、调用方法
        brandMapper.add(brand);
        sqlSession.commit();

        // 5、释放资源
        sqlSession.close();

    }

    @Override
    public void deleteByIds(int[] ids) {
        // 2、获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4、调用方法
        brandMapper.deleteByIds(ids);
        sqlSession.commit();

        // 5、释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        // 2、获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 4、查询当前页数据
        List<Brand> brands = brandMapper.selectByPage(begin, size);

        // 5、查询总记录数
        int totalCount = brandMapper.selectTotalCount();

        // 6、封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        // 7、释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        // 2、获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取BrandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;


        // 处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        // 4、查询当前页数据
        List<Brand> brands = brandMapper.selectByPageAndCondition(begin, size, brand);

        // 5、查询总记录数
        int totalCount = brandMapper.selectTotalCountByCondition(brand);

        // 6、封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        // 7、释放资源
        sqlSession.close();

        return pageBean;
    }
}
