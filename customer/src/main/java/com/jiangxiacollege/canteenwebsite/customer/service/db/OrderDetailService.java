package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.OrderDetail;

import java.util.List;


public interface OrderDetailService extends IService<OrderDetail> {

    ResponseBase batchAddOrderDetail(List<OrderDetail> list);

}
