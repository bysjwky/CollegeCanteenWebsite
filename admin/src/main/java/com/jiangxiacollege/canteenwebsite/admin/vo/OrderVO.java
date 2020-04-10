package com.jiangxiacollege.canteenwebsite.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data

public class OrderVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long seller_id;
    private Long customer_id;
    private  Long product_id;
    private  Long address_id;
    private int  status;
    private BigDecimal money;
    private String pay;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private String edit;

    private String userId;

    private String name;
    private String school;
}
