package com.jiangxiacollege.canteenwebsite.customer.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderVo implements Serializable{
    private Long id;
    private Long orderId;
    private Long sellerId;
    private Long customerId;
    private  Long productId;
    private String place;
    private int status;
    private BigDecimal money;
    private String pay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private String edit;
    private BigDecimal price;
    private  int number;
    private String name;
    private String address;
    private String productName;
    private Long cartId;



}
