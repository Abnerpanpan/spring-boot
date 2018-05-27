package com.pan.springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pan.springboot.dao.ArticleMapper;
import com.pan.springboot.dao.CommentMapper;
import com.pan.springboot.entity.*;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BlogSericeImpl implements BlogService {
    @Autowired
    ArticleMapper articleDao;

    @Autowired
    CommentMapper commentDao;
    @Override
    public Article selectByPrimaryKey(Long articleId) {
        Article article = articleDao.selectByPrimaryKey(articleId);
        Article temp = new Article();
        temp.setArticleId(articleId);
        temp.setClickCount(article.getClickCount()+1);
        articleDao.updateByPrimaryKeySelective(temp);
        return article;
    }

    @Override
    public int saveArticle(Article article, HttpSession session) throws IllegalException {
        User existUser = (User) session.getAttribute("user");
        if(existUser==null){
            throw new IllegalException("八戒，你是逃不出为为师手掌心的~");
        }
        article.setUserId(existUser.getUserId());
        article.setCreatTime(new Date());
        int isSaved = articleDao.insertSelective(article);
        if(isSaved>0){
            return 100;
        }
        return -1;
    }

    @Override
    public PageInfo<ArticleCustom> selectArticle(Integer pageNum) {
        int pageCount=10;
        if(pageNum==null){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,pageCount);
        List<ArticleCustom> articleList = articleDao.selectArticle();
        PageInfo<ArticleCustom> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public int saveComment(Article article, Comment comment, HttpSession session) throws IllegalException {
        if(session.getAttribute("user")==null){
            throw new IllegalException("八戒,你又调皮了~");
        }
        User user= (User) session.getAttribute("user");
        comment.setArticleId(article.getArticleId());
        comment.setUserId(user.getUserId());
        comment.setTimestamp(new Date());
        comment.setIsread(0);
        if(comment.getParentCommentId()==null){
            comment.setParentCommentId(0L);
            comment.setFloor(commentDao.countFloor(article.getArticleId())+1L);
        }else {
            comment.setFloor(0L);
            Comment temp = commentDao.selectByPrimaryKey(comment.getParentCommentId());
            Integer hasChild = temp.getHasChild();
            temp.setHasChild(hasChild+1);
            commentDao.updateByPrimaryKeySelective(temp);
        }
       int isSaved = commentDao.insertSelective(comment);
        if(isSaved==0){
            return -1;
        }
        return 100;
    }

    @Override
    public List<CommentCustom> selectCommentByArticleId(Long ArticleId) {
        return commentDao.selectCommentByArticleId(ArticleId) ;
    }

    @Override
    public List<CommentCustom> selectCommentSecond(Long floor) {
        return commentDao.selectCommentSecond(floor);
    }

    @Override
    public List<Object> selectComment(Long ArticleId) {
        List<CommentCustom> commentCustoms = commentDao.selectCommentByArticleId(ArticleId);
        ArrayList<Object> commentList = new ArrayList<>();
        for(CommentCustom comment:commentCustoms){
            HashMap<String, Object> commentMap = new HashMap<>();
            commentMap.put("commentRoot",comment);
            List<CommentCustom> commentSecond = commentDao.selectCommentSecond(comment.getCommentId());
            commentMap.put("commentSecond",commentSecond);
            commentList.add(commentMap);
        }
        return commentList;
    }

    @Override
    public ArticleCustom selectByPrimaryKeyCustom(Long ArticleId) {
        return articleDao.selectByPrimaryKeyCustom(ArticleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Article record) {
        int i = articleDao.updateByPrimaryKeySelective(record);
        if(i>0){
            return 100;
        }else {
            return -1;
        }
    }

    @Override
    public int deletComment(Long commentId) {
        commentDao.deletByParentId(commentId);
        int i = commentDao.deleteByPrimaryKey(commentId);
        if(i>0){
            return 100;
        }
        return -1;
    }


}
