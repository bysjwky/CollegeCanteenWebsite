package com.jiangxiacollege.canteenwebsite.admin.model;
import java.sql.Timestamp;

/**
 * 与数据库中的物理表映射，增删改一般都用这个模型实现
 */
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName("t_sys_user")//数据库对应表名
public class User {
	
	@TableId
	private String id;//@TableId表示主键，与字段名称大小写一致
	
	private String username;//与字段名称大小写一致
	private String password;//与字段名称大小写一致
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp birthday;//与字段名称大小写一致
	
	private String photo;//与字段名称大小写一致
	private String introduce;//与字段名称大小写一致
	private String usertype;//与字段名称大小写一致
	private String seller_id;//与字段名称大小写一致


	
}
