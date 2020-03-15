package com.jiangxiacollege.canteenwebsite.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jiangxiacollege.canteenwebsite.admin.model.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.admin.vo.SellerUserInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerMapper extends BaseMapper<SellerUserInfo> {

    List<SellerUserInfoVO> selectSellerListPage(Pagination page, SellerUserInfoVO sellerUserInfoVO);
}
