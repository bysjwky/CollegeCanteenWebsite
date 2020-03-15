package com.jiangxiacollege.canteenwebsite.admin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.RoleMapper;
import com.jiangxiacollege.canteenwebsite.admin.mapper.UserRoleMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.Role;
import com.jiangxiacollege.canteenwebsite.admin.model.User;
import com.jiangxiacollege.canteenwebsite.admin.model.UserRole;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.RoleVO;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;// 注入mapper进行数据操作
	
	@Autowired
	private UserRoleMapper userRoleMapper;// 注入mapper进行数据操作

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public int delete(String ids) {
		// ids,逗号隔开的主键
		List<String> listid = Convert.toListStrArray(ids);
		return roleMapper.deleteBatchIds(listid);
	}

	public Role selectById(String id) {
		// roleMapper.selectOne(role),selectOne可以按照其他字段来查询一条记录
		return roleMapper.selectById(id);
	}

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public int updateById(Role role) {
		return roleMapper.updateById(role);
	}

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public int insert(Role role) {
		// 添加雪花主键id
		role.setId(SnowflakeIdWorker.getUUID());
		int n = roleMapper.insert(role);
//			n=1/0; //事务测试
		return n;
	}
	
	/**
	 * 采用集成的查询方法
	 * @param username
	 * @return
	 */
	public List<Role> selectList() {
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
//	    wrapper.like("username", username);//可以多个条件
		return roleMapper.selectList(wrapper);
	}
	
	/**
	 * 返回用户拥有的角色
	 * @param user
	 * @return
	 */
	public List<UserRole> selectUserRole(User user){
		EntityWrapper<UserRole> wrapper = new EntityWrapper<UserRole>();
	    wrapper.eq("sys_user_id", user.getId());//可以多个条件
		return userRoleMapper.selectList(wrapper);
	}
	
	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public void assignRoles(User user,String ids) {
		//1.清除原有分配
		EntityWrapper<UserRole> wrapper = new EntityWrapper<UserRole>();
	    wrapper.eq("sys_user_id", user.getId());//可以多个条件
	    userRoleMapper.delete(wrapper);	    
		//2.重新插入角色
	    if(!StringUtils.isEmpty(ids)) {
	    	List<String> listrid = Convert.toListStrArray(ids);
	    	SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
		    for(String rid:listrid) {
		    	UserRole uRole=new UserRole();
		    	uRole.setId(String.valueOf(idWorker.nextId()));
		    	uRole.setSys_user_id(user.getId());
		    	uRole.setSys_role_id(rid);
		    	userRoleMapper.insert(uRole);
		    }
	    }	    
	}

	/**
	 * 分页查询
	 * 
	 * @param role
	 * @return
	 */
	public DataTableResult selectRoleListPage(RoleVO roleVO,int start,int length,String orderField,String orderDir) {
		Page<RoleVO> page = null;
		//排序
		if(!StringUtils.isEmpty(orderDir)&&!StringUtils.isEmpty(orderField)) {
			if(orderDir.equals("asc")) {
				page = new Page<>(start/length + 1, length,orderField,true);// 当前页，每页总条数 构造 page 对象
			}else {
				page = new Page<>(start/length + 1, length,orderField,false);// 当前页，每页总条数 构造 page 对象
			}
			
		}else {
			page = new Page<>(start/length + 1, length,"id",false);//默认id降序
		}
        page.setRecords(roleMapper.selectRoleListPage(page, roleVO));
        
		DataTableResult result = new DataTableResult();
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		result.setData(page.getRecords());
		return result;
	}

	public UserRole getOne(String userId){
		UserRole userRole = new UserRole();
		userRole.setSys_user_id(userId);
		return userRoleMapper.selectOne(userRole);
	}

	@Transactional
	public int insertUserRole(UserRole userRole){

		return userRoleMapper.insert(userRole);
	}



}

