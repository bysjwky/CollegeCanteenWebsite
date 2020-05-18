package com.jiangxiacollege.canteenwebsite.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CartVo implements Serializable{

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long productId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sellerId;
    private int number;

    private String shopName;
    private  String photo;
    private  String name;
    private  BigDecimal price;
    private  BigDecimal totalPrice;

}
