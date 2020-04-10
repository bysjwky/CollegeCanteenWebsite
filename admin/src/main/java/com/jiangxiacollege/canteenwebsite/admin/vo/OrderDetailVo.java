package com.jiangxiacollege.canteenwebsite.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetailVo implements Serializable{
    private Long id;
    private Long order_id;
    private  Long product_id;
    private Long address_id;
    private  int number;
    private String name;
    private BigDecimal price;
    private String address;
    private  String shop_name;
    private  BigDecimal totalPrice;
    private  String edit;
    private String nc;
    private String phone;
    private Long customer_id;
    private Long seller_id;





}
