package com.jiangxiacollege.canteenwebsite.customer.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("comment")
@Data
public class Comment implements Serializable{
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long productId;
    private Long customerId;
    private Long sellerId;
    private Long orderId;
    private String writing;



}
