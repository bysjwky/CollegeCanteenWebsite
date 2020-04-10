package com.jiangxiacollege.canteenwebsite.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangxiacollege.canteenwebsite.admin.mapper.ProductMapper;
import com.jiangxiacollege.canteenwebsite.admin.mapper.TotalMapper;
import com.jiangxiacollege.canteenwebsite.admin.mapper.UserMapper;
import com.jiangxiacollege.canteenwebsite.admin.model.Product;
import com.jiangxiacollege.canteenwebsite.admin.util.Convert;
import com.jiangxiacollege.canteenwebsite.admin.util.SnowflakeIdWorker;
import com.jiangxiacollege.canteenwebsite.admin.vo.DataTableResult;
import com.jiangxiacollege.canteenwebsite.admin.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TotalService {
    @Autowired
    private TotalMapper totalMapper;//注入mapper进行数据操作

}
