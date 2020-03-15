package com.jiangxiacollege.canteenwebsite.admin.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 展示模型（一般不做修改操作），不一定与物理表字段一致，可以是几张关联表的字段组合
 * @author gjq
 *
 */
@Data
public class UserVO{

	private String id;
	
	private String username;
	private String password;
	
    private Timestamp birthday;
	
	private String photo;
	private String introduce;
	private String usertype;
	private String seller_id;
	
//	private String rolename;//关联角色表中的name字段，用来存放角色名



//	public String getRolename() {
//		return rolename;
//	}
//
//	public void setRolename(String rolename) {
//		this.rolename = rolename;
//	}
	
	
}
