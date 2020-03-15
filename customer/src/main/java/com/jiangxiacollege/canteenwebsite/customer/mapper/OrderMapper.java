package com.jiangxiacollege.canteenwebsite.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangxiacollege.canteenwebsite.customer.table.Orders;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderDetailVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderMapper extends BaseMapper<Orders> {

        @Select("SELECT ord.*,cui.user_name  name  FROM `orders` ord LEFT JOIN customer_user_info cui ON ord.customer_id = cui.id where ord.customer_id = #{customerId}")
        List<OrderVo> orderInfo(String customerId);

 @Select("SELECT od.id,od.product_id,o.id orderId ,o.customer_id,o.seller_id,sui.shop_name ,p.name,od.number,p.price,o.edit,a.name nc,a.phone,a.address\n" +
         "FROM `order_detail` od \n" +
         "LEFT JOIN `orders` o ON od.order_id = o.id\n" +
         "LEFT JOIN `product` p ON od.product_id = p.id \n" +
         "LEFT JOIN `seller_user_info` sui ON o.seller_id=sui.id \n" +
         "LEFT JOIN `address` a ON o.address_id=a.id \n" +
         "where od.order_id= #{orderId}")
 List<OrderDetailVo> orderDetail(String orderId);

        }
