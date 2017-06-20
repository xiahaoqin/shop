package com.chinasoft.shop.mapper;

import com.chinasoft.shop.pojo.Comment;

public interface CommentMapper {
    int insert(Comment record);

    int insertSelective(Comment record);
}