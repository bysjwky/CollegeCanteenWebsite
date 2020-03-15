package com.jiangxiacollege.canteenwebsite.customer.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetailVo implements Serializable{
    private Long id;
    private Long orderId;
    private  Long productId;
    private Long addressId;
    private  int number;
    private String name;
    private BigDecimal price;
    private String address;
    private  String shopName;
    private  BigDecimal totalPrice;
    private  String edit;
    private String nc;
    private String phone;
    private Long customerId;
    private Long sellerId;





}
