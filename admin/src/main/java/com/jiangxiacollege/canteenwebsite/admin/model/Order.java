package com.jiangxiacollege.canteenwebsite.admin.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
@TableName("orders")
public class Order {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long sellerId;
    private Long customerId;

    private  Long addressId;

    private int  status;
    private BigDecimal money;
    private String pay;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private String edit;

}
