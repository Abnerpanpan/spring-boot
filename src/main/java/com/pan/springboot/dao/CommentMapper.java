package com.pan.springboot.dao;

import com.pan.springboot.entity.Comment;
import com.pan.springboot.entity.CommentCustom;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentCustom> selectCommentByArticleId(Long ArticleId);

    Long countFloor(Long ArticleId);

    List<CommentCustom> selectCommentSecond(Long floor);

    int deletByParentId(Long commentId);
}