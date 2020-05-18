package com.jiangxiacollege.canteenwebsite.customer.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetailVo implements Serializable{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long productId;
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sellerId;





}
