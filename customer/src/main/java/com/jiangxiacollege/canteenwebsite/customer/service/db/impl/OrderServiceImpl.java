package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.OrderMapper;
import com.jiangxiacollege.canteenwebsite.customer.mapper.ProductMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderDetailService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.OrderService;
import com.jiangxiacollege.canteenwebsite.customer.service.db.ProductService;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;
import com.jiangxiacollege.canteenwebsite.customer.table.Orders;
import com.jiangxiacollege.canteenwebsite.customer.table.OrderDetail;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderDetailVo;
import com.jiangxiacollege.canteenwebsite.customer.vo.OrderVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {


    @Resource
    public CartService cartService;

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private ProductService productService;




//订单列表
    @Override
    public ResponseBase orderInfo(String customerId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<OrderVo> orderList = this.baseMapper.orderInfo(customerId);
            responseBase.setData(orderList);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("订单查询错误");
        }
        return responseBase;
    }

    //订单详情
    @Override
    public ResponseBase orderDetail(String orderId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<OrderDetailVo> list = this.baseMapper.orderDetail(orderId);
            for(OrderDetailVo orderDetailVo : list){
                BigDecimal totalPrice = orderDetailVo.getPrice().multiply(new BigDecimal(orderDetailVo.getNumber()));
                orderDetailVo.setTotalPrice(totalPrice);
            }
            responseBase.setData(list);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("订单查询错误");
        }
        return responseBase;
    }
//订单生成
    @Override
    @Transactional
    public ResponseBase addOrder(Orders order, List<OrderDetail> list,List cartIdList) {
        ResponseBase responseBase = new ResponseBase();
        Boolean flag = this.save(order);
        int flag2 = orderDetailService.batchAddOrderDetail(list).getCode();
        if (flag&&flag2==0){
            responseBase.setCode(0);
            //产品销量更新

            for (OrderDetail od : list ){
            UpdateWrapper<Product> uw = new UpdateWrapper<>();
            uw.eq("id",od.getProductId());
            uw.setSql("sale=sale+"+od.getNumber());
                productService.update(uw);
            }
            //删除购物车
            cartService.removeByIds(cartIdList);

        }else {
            responseBase.setCode(1);
            responseBase.setMessage("订单新增错误");
        }
        return responseBase;
    }
//删除订单
    @Override
    @Transactional
    public ResponseBase delOrder(String orderId) {
        ResponseBase responseBase = new ResponseBase();
        Boolean flag = this.removeById(orderId);
        if (flag){
            QueryWrapper<OrderDetail> qw = new QueryWrapper<>();
            qw.eq("order_id",orderId);
            Boolean flag1 = orderDetailService.remove(qw);
            if (flag1){
                responseBase.setCode(0);
                responseBase.setMessage("订单删除成功");
            }else {
                responseBase.setCode(1);
                responseBase.setMessage("订单删除失败");
            }
        }else {
            responseBase.setCode(1);
            responseBase.setMessage("订单删除失败");
        }
        return responseBase;
    }
//订单送达
    @Override
    @Transactional
    public ResponseBase updateOrder(String orderId) {
        ResponseBase responseBase = new ResponseBase();
        Orders orders =new Orders();
        orders.setId(Long.valueOf(orderId)) ;

        int status=2;
        orders.setStatus(status);
//        String sql="update orders  set status=2 WHERE id='orderId'";
        Boolean flag = this.updateById(orders);
        if (flag){
            responseBase.setCode(0);
            responseBase.setMessage("订单确认送达成功");
        }else {
            responseBase.setCode(1);
            responseBase.setMessage("订单确认送达失败");
        }
        return responseBase;
    }




}

