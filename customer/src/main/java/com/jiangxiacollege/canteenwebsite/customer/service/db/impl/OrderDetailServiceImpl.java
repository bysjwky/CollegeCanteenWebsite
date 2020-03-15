package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.OrderDetailMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderDetailService;
import com.jiangxiacollege.canteenwebsite.customer.table.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Override
    public ResponseBase batchAddOrderDetail(List<OrderDetail> list) {

        ResponseBase responseBase = new ResponseBase();
        Boolean flag = this.saveBatch(list);
        if (flag){
            responseBase.setCode(0);
        }else {
            responseBase.setCode(1);
            responseBase.setMessage("订单详情增加错误");
        }
        return responseBase;
    }
}

