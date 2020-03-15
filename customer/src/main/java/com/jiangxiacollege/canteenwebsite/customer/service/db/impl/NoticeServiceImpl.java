package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.NoticeMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.NoticeService;
import com.jiangxiacollege.canteenwebsite.customer.table.Notice;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


    @Override
    public ResponseBase noticeList() {
        ResponseBase responseBase=new ResponseBase();
        try {
            LambdaQueryWrapper<Notice> gg=new QueryWrapper().lambda();
          gg.in(Notice::getId,new Integer[]{0, 1,2,3,4,5,6,7});
            List<Notice> list=this.list(gg);
            responseBase.setData(list);

        }catch(Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("公告查询列表错误");
        }
        return responseBase;
    }
}