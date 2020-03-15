package com.jiangxiacollege.canteenwebsite.customer.service.db.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.mapper.CommentMapper;
import com.jiangxiacollege.canteenwebsite.customer.service.db.CommentService;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;
import com.jiangxiacollege.canteenwebsite.customer.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

//评论信息
    @Override
    public ResponseBase commentInfo(String productId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            List<CommentVo> commentList = this.baseMapper.commentInfo(productId);
            responseBase.setData(commentList);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("评论查询错误");
        }
        return responseBase;
    }
//评论条数
    @Override
    public ResponseBase commentCount(String productId) {
        ResponseBase responseBase = new ResponseBase();
        try {
            LambdaQueryWrapper<Comment> lq = new QueryWrapper().lambda();
            lq.eq(Comment::getProductId,productId);
            int count = this.count(lq);
            responseBase.setData(count);

        }catch (Exception e){
            log.error(e.getMessage());
            responseBase.setCode(1);
            responseBase.setMessage("评论条数查询错误");
        }
        return responseBase;
    }
//写评论
    @Override
    public ResponseBase plOrder(Comment comment) {
            ResponseBase responseBase = new ResponseBase();
            Boolean flag = this.save(comment);
            if (flag){
                responseBase.setCode(0);
                responseBase.setMessage("评论成功");
            }else {
                responseBase.setCode(1);
                responseBase.setMessage("评论失败");
            }
            return responseBase;
        }

}
