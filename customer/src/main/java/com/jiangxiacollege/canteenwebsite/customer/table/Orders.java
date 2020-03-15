package com.jiangxiacollege.canteenwebsite.customer.table;

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
import java.util.Date;

@TableName("orders")
@Data
public class Orders implements Serializable {


    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long sellerId;
    private Long customerId;

    private  Long addressId;

    private int  status;
    private BigDecimal money;
    private String pay;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String edit;



}
