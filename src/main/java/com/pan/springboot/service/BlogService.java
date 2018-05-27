package com.pan.springboot.service;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.Article;
import com.pan.springboot.entity.ArticleCustom;
import com.pan.springboot.entity.Comment;
import com.pan.springboot.entity.CommentCustom;
import com.pan.springboot.exception.IllegalException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BlogService {

    Article selectByPrimaryKey(Long articleId);

    int saveArticle(Article article, HttpSession session) throws IllegalException;

    PageInfo<ArticleCustom> selectArticle(Integer pageNum);

    int saveComment(Article article, Comment comment, HttpSession session) throws IllegalException;

    List<CommentCustom> selectCommentByArticleId(Long ArticleId);

    List<CommentCustom> selectCommentSecond(Long floor);

    List<Object> selectComment(Long ArticleId);

    ArticleCustom selectByPrimaryKeyCustom(Long ArticleId);

    int updateByPrimaryKeySelective(Article record);

    int deletComment(Long commentId);

}
