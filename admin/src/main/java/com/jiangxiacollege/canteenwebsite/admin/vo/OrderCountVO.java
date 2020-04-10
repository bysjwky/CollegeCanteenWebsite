package com.jiangxiacollege.canteenwebsite.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data

public class OrderCountVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long seller_id;
    private String user_name;
    private String school;
    private int nameNum;
    private int schoolNum;
}
