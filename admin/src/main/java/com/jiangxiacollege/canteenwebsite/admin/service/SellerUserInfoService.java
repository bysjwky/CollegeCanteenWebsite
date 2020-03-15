package com.jiangxiacollege.canteenwebsite.admin.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.SellerUserInfoMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.ResponseBase;
import com.jiangxiacollege.canteenwebsite.admin.model.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.admin.model.User;
import com.jiangxiacollege.canteenwebsite.admin.model.UserRole;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.Json;
import com.jiangxiacollege.canteenwebsite.admin.vo.SellerUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SellerUserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(SellerUserInfoService.class);

    @Autowired
    private SellerUserInfoMapper sellerUserInfoMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;// 注入业务层的service
    @Autowired
    private RoleService roleService;




    @Transactional
    public ResponseBase addUser(SellerUserInfo sellerUserInfo){
        ResponseBase responseBase = new ResponseBase();
          int a = sellerUserInfoMapper.insert(sellerUserInfo);
          if (a==1){
              responseBase.setCode(0);
              responseBase.setMessage("新增成功");
          }else {
              responseBase.setCode(1);
              responseBase.setMessage("新增失败");
          }
        return responseBase;

    }



    @Transactional
    public int delete(String ids) {
        //ids,逗号隔开的主键
        List<String> listid= Convert.toListStrArray(ids);
        return sellerUserInfoMapper.deleteBatchIds(listid);
    }

    public SellerUserInfo selectById(String id) {
        //userMapper.selectOne(user),selectOne可以按照其他字段来查询一条记录
        return sellerUserInfoMapper.selectById(id);
    }

    public SellerUserInfo selectByUser(SellerUserInfo sellerUserInfo) {
        return sellerUserInfoMapper.selectOne(sellerUserInfo);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int updateById(SellerUserInfo sellerUserInfo) {
        return sellerUserInfoMapper.updateById(sellerUserInfo);
    }

    //对于执行数据修改的方法加上事务处理
    @Transactional
    public int insert(SellerUserInfo sellerUserInfo) {
        //添加雪花主键id
        sellerUserInfo.setId(Long.parseLong(SnowflakeIdWorker.getUUID()));
        int n = sellerUserInfoMapper.insert(sellerUserInfo);
        //密码安全一点的话，不能原文保存，应该用MD5，也可以加盐处理
//		n=1/0; //事务测试
        return n;
    }

    public List<SellerUserInfo> selectList(String user_name) {
        EntityWrapper<SellerUserInfo> wrapper = new EntityWrapper<SellerUserInfo>();
        wrapper.like("user_name", user_name);
        return sellerUserInfoMapper.selectList(wrapper);
    }

    public DataTableResult selectSellerUserInfoListPage(SellerUserInfoVO sellerUserInfoVO, int start, int length, String orderField, String orderDir) {
        Page<SellerUserInfoVO> page = null;
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
        page.setRecords(sellerUserInfoMapper.selectSellerUserInfoListPage(page, sellerUserInfoVO));

        DataTableResult result = new DataTableResult();
        result.setRecordsTotal(page.getTotal());
        result.setRecordsFiltered(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

//管理员商家审核
    @Transactional
    public Json auditById(SellerUserInfo sellerUserInfo){
        Json j = new Json();
        if (this.updateById(sellerUserInfo) > 0) {
            j.setSuccess(true);
            j.setMsg("修改成功！");
            if(sellerUserInfo.getStatus()==1){
                SellerUserInfo seller =this.selectById(String.valueOf(sellerUserInfo.getId()));
                String userName = seller.getUser_name();
                String password = seller.getPassword();
                String photo = seller.getPhoto();

                User user = new User();
                user.setSeller_id(String.valueOf(sellerUserInfo.getId()));
                user.setUsername(userName);
                user.setPassword(password);
                user.setPhoto(photo);
                user.setUsertype("普通用户");
                userService.insert(user);

                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                UserRole userRole = new UserRole();
                userRole.setId(String.valueOf(idWorker.nextId()));
                userRole.setSys_user_id(user.getId());
                userRole.setSys_role_id("3");
                roleService.insertUserRole(userRole);

            }
        } else {
            j.setSuccess(false);
            j.setMsg("修改失败！");
        }
        return j;

    }


}
