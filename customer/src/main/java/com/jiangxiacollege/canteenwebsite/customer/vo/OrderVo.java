package com.jiangxiacollege.canteenwebsite.customer.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderVo implements Serializable{
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sellerId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cartId;



}
