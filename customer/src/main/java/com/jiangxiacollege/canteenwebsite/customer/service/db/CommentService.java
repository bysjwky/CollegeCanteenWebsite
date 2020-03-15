package com.jiangxiacollege.canteenwebsite.customer.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangxiacollege.canteenwebsite.customer.common.ResponseBase;
import com.jiangxiacollege.canteenwebsite.customer.table.Comment;

public interface CommentService extends IService<Comment> {

    ResponseBase commentInfo(String productId); //评论信息

    ResponseBase commentCount(String productId);  //评论计数

    ResponseBase plOrder(Comment comment);
}
