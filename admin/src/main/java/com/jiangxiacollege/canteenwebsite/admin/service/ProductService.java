package com.jiangxiacollege.canteenwebsite.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.ProductMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.Product;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int delete(String ids) {
        //ids,逗号隔开的主键
        List<String> listid= Convert.toListStrArray(ids);
        return productMapper.deleteBatchIds(listid);
    }

    public Product selectById(String id) {
        //userMapper.selectOne(user),selectOne可以按照其他字段来查询一条记录
        return productMapper.selectById(id);
    }

    public Product selectByCustomer(Product product) {
        return productMapper.selectOne(product);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int updateById(Product product) {
        return productMapper.updateById(product);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int insert(Product product) {
        //添加雪花主键id
        product.setId(SnowflakeIdWorker.getUUID());
        int n = productMapper.insert(product);
        //密码安全一点的话，不能原文保存，应该用MD5，也可以加盐处理
//		n=1/0; //事务测试
        return n;
    }

    public List<Product> selectList(String seller_id) {
        EntityWrapper<Product> wrapper =new EntityWrapper<Product>();
        wrapper.like("seller_id", seller_id);
        return productMapper.selectList(wrapper);
    }
//商家销量统计
    public List<Product> selectListBySellerId(String sellerId) {
        EntityWrapper<Product> wrapper =new EntityWrapper<Product>();
        wrapper.eq("seller_id", sellerId);
        return productMapper.selectList(wrapper);
    }

    /**
     * 分页查询
     * @param
     * @return
     */
    public DataTableResult selectProductListPage(ProductVO productVO, int start, int length, String orderField, String orderDir) {
        Page<ProductVO> page = null;
        //排序
        if(!StringUtils.isEmpty(orderDir)&&!StringUtils.isEmpty(orderField)) {
            if(orderDir.equals("asc")) {
                page = new Page<>(start/length + 1, length,orderField,true);// 当前页，每页总条数 构造 page 对象
            }else {
                page = new Page<>(start/length + 1, length,orderField,false);// 当前页，每页总条数 构造 page 对象
            }

        }else {
            page = new Page<>(start/length + 1, length,"id",false);//默认id降序
        }
        page.setRecords(productMapper.selectProductListPage(page, productVO));

        DataTableResult result = new DataTableResult();
        result.setRecordsTotal(page.getTotal());
        result.setRecordsFiltered(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }


}
