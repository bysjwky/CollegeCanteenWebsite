package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;
import com.jiangxiacollege.canteenwebsite.customer.table.Orders;
import com.jiangxiacollege.canteenwebsite.customer.table.OrderDetail;
import org.springframework.core.annotation.Order;

import java.util.List;


public interface OrderService extends IService<Orders> {
    ResponseBase orderInfo(String customerId); //订单列表
    ResponseBase orderDetail(String orderId); //订单详情

    ResponseBase addOrder(Orders order, List<OrderDetail> list,List cartIdList); //生成订单数据和订单详情数据

    ResponseBase delOrder(String orderId); //订单删除
    ResponseBase updateOrder(String orderId); //订单送达修改


}
