package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.SellerUserMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.SellerUserService;
import com.jiangxiacollege.canteenwebsite.customer.table.Product;
import com.jiangxiacollege.canteenwebsite.customer.table.SellerUserInfo;
import com.jiangxiacollege.canteenwebsite.customer.utils.ImageUtil2;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerUserServiceImpl extends ServiceImpl<SellerUserMapper, SellerUserInfo> implements SellerUserService {


    @Value("${pf}")
    private String pf;
    //首页商家
    @Override
    public ResponseBase sellerList() {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqs=new QueryWrapper().lambda();
            lqs.in(SellerUserInfo::getId,new Integer[]{0, 1,2,3,4,5,6});
            List<SellerUserInfo> list= this.list(lqs);
            for(SellerUserInfo sellerUserInfo : list){
                String photoSrc = sellerUserInfo.getPhoto();
                if (null!=photoSrc&&!"".equals(photoSrc)) {
                    String path = pf + sellerUserInfo.getPhoto().replace("\\\\", "\\\\\\\\");
                    String suffix = path.split("\\.")[1];
                    sellerUserInfo.setPhoto("data:image/" + suffix + ";base64," + ImageUtil2.GetImageStr(path));
                }
            }
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询列表错误");
        }
        return responseBase;
    }
//订餐商家列表
    @Override
    public ResponseBase sellerLists() {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqss=new QueryWrapper().lambda();
            lqss.isNotNull(SellerUserInfo::getId);
            List<SellerUserInfo> list= this.list(lqss);
            for(SellerUserInfo sellerUserInfo : list){
                String photoSrc = sellerUserInfo.getPhoto();
                if (null!=photoSrc&&!"".equals(photoSrc)) {
                    String path = pf + sellerUserInfo.getPhoto().replace("\\\\", "\\\\\\\\");
                    String suffix = path.split("\\.")[1];
                    sellerUserInfo.setPhoto("data:image/" + suffix + ";base64," + ImageUtil2.GetImageStr(path));
                }
            }
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询列表错误");
        }

        return responseBase;
    }
//商家具体信息
    @Override
    public ResponseBase sellerInfo(String sellerId) {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqs=new QueryWrapper().lambda();
            lqs.eq(SellerUserInfo::getId,sellerId);
            SellerUserInfo sellerUserInfo= this.getOne(lqs);
            String photoSrc = sellerUserInfo.getPhoto();
            if (null!=photoSrc&&!"".equals(photoSrc)) {
                String path = pf + sellerUserInfo.getPhoto().replace("\\\\", "\\\\\\\\");
                String suffix = path.split("\\.")[1];
                sellerUserInfo.setPhoto("data:image/" + suffix + ";base64," + ImageUtil2.GetImageStr(path));
            }
            responseBase.setData(sellerUserInfo);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询信息错误");
        }
        return responseBase;
    }
//搜索商家
    @Override
    public ResponseBase sellerList(String keyWord) {
        ResponseBase responseBase= new ResponseBase();
        try{
            LambdaQueryWrapper<SellerUserInfo> lqss=new QueryWrapper().lambda();
            lqss.like(SellerUserInfo::getShopName,keyWord);
            List<SellerUserInfo> list= this.list(lqss);
            for(SellerUserInfo sellerUserInfo : list){
                String photoSrc = sellerUserInfo.getPhoto();
                if (null!=photoSrc&&!"".equals(photoSrc)) {
                    String path = pf + sellerUserInfo.getPhoto().replace("\\\\", "\\\\\\\\");
                    String suffix = path.split("\\.")[1];
                    sellerUserInfo.setPhoto("data:image/" + suffix + ";base64," + ImageUtil2.GetImageStr(path));
                }
            }
//            responseBase.setCode(0);
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("商家查询列表错误");
        }

        return responseBase;
    }


}
