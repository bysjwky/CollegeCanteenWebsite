package com.jiangxiacollege.canteenwebsite.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.Order;
import com.jiangxiacollege.canteenwebsite.admin.vo.OrderDetailVo;
import com.jiangxiacollege.canteenwebsite.admin.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order>
{
    List<OrderVO> selectOrderListPage(Pagination page, OrderVO orderVO);

    @Select("SELECT od.id,od.product_id,o.id orderId ,o.customer_id,o.seller_id,sui.shop_name ,p.name,od.number,p.price,o.edit,a.name nc,a.phone,a.address\n" +
            "FROM `order_detail` od \n" +
            "LEFT JOIN `orders` o ON od.order_id = o.id\n" +
            "LEFT JOIN `product` p ON od.product_id = p.id \n" +
            "LEFT JOIN `seller_user_info` sui ON o.seller_id=sui.id \n" +
            "LEFT JOIN `address` a ON o.address_id=a.id \n" +
            "where od.order_id= #{orderId}")
    List<OrderDetailVo> orderDetail(String orderId);

}
