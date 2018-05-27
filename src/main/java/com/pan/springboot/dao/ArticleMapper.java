package com.pan.springboot.dao;

import com.pan.springboot.entity.Article;
import com.pan.springboot.entity.ArticleCustom;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<ArticleCustom> selectArticle();

    ArticleCustom selectByPrimaryKeyCustom(Long articleId);
}