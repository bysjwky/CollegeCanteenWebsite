package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;



import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.CartMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CartService;
import com.jiangxiacollege.canteenwebsite.customer.table.Cart;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.ImageUtil2;
import com.jiangxiacollege.canteenwebsite.customer.vo.CartVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {


    @Value("${pf}")
    private String pf;
    @Override
    public ResponseBase cartList(String customerId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<CartVo> list = this.baseMapper.cartList(customerId);
            /*for(CartVo cartVo : list){
                BigDecimal totalPrice = cartVo.getPrice().multiply(new BigDecimal(cartVo.getNumber()));
                cartVo.setTotalPrice(totalPrice);
            }*/
            for(CartVo cartVo : list){
                String path = pf+cartVo.getPhoto().replace("\\\\","\\\\\\\\");
                String suffix = path.split("\\.")[1];
                cartVo.setPhoto("data:image/"+suffix+";base64,"+ ImageUtil2.GetImageStr(path));
            }
            responseBase.setData(list);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("订单查询错误");
        }
        return responseBase;
    }

    @Transactional
    @Override
    public ResponseBase addCart(Cart cart) {
        ResponseBase responseBase = new ResponseBase();
        int flag = this.baseMapper.insert(cart);
        if (flag>0){
            responseBase.setCode(0);
            responseBase.setData(flag);
        }else {
            responseBase.setCode(1);
            responseBase.setData("加入购物车错误");
        }
        return responseBase;
    }



    @Override
    public ResponseBase delCart(Long customerId, Long productId) {

        ResponseBase responseBase = new ResponseBase();
        UpdateWrapper<Cart> uw = new UpdateWrapper<>();
        uw.eq("customer_id",customerId);
        uw.eq("product_id",productId);
        int flag = this.baseMapper.delete(uw);
        if (flag>0){
            responseBase.setCode(0);
            responseBase.setData(flag);
        }else {
            responseBase.setCode(1);
            responseBase.setData("删除商品错误");
        }
        return responseBase;




    }


}
