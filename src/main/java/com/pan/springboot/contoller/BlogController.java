package com.pan.springboot.contoller;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.*;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.BlogService;
import com.pan.springboot.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BlogController {
    @Autowired
	BlogService blogService;

    @Autowired
    SelectionService selectionService;

    @RequestMapping("/toBlog")
    public  String toBlog(Integer pageNum ,ModelMap model){
        PageInfo<ArticleCustom> pageInfo = blogService.selectArticle(pageNum);
        List<ArticleCustom> articleList = pageInfo.getList();
        model.addAttribute("dataList",articleList);
        model.addAttribute("totalPages",pageInfo.getPages());
        return "blogpages/blog";
    }

    @RequestMapping("/showMoreBlog")
    public @ResponseBody Object showMoreBlog(Integer pageNum , ModelMap model){
        //初始化pageNum,防止恶意访问时为空
        if(pageNum==null){
            pageNum=1;
        }
        PageInfo<ArticleCustom> pageInfo = blogService.selectArticle(pageNum);
        //判断pageNum和
        if(pageNum>pageInfo.getPages()){
            return -1;
        }
        return pageInfo.getList();
    }

    @RequestMapping(value = "/newBlog")
    public String toNewBlog(ModelMap model,HttpSession session) throws IllegalException {
        if(session.getAttribute("user")==null){
            throw new IllegalException("八戒,先去登录吧~");
        }
        List<Selection> selections = selectionService.find();
        model.addAttribute("dataList",selections);
        return "blogpages/newblog";
    }

    @RequestMapping(value = "/editBlog")
    public String editBlog(Long articleId,ModelMap model){
        ArticleCustom articleCustom = blogService.selectByPrimaryKeyCustom(articleId);
        model.addAttribute("dataList",articleCustom);
        return "blogpages/editblog";
    }

    @ResponseBody
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    public int updateArticle(Article article){
        return blogService.updateByPrimaryKeySelective(article);
    }

    @RequestMapping(value = "/toBlogPage",method = RequestMethod.GET)
    public String toBlogPage(Long articleId, ModelMap model){
        Article article = blogService.selectByPrimaryKey(articleId);
        List<Object> commentList = blogService.selectComment(articleId);
        model.addAttribute("commentList",commentList);
        model.addAttribute("dataList",article);
        return "blogpages/blogpage";
    }

    @RequestMapping(value = "/saveArticle",method = RequestMethod.POST)
    public @ResponseBody int saveArticle(Article article,HttpSession session) throws IllegalException {
        int isSaved = blogService.saveArticle(article, session);
        return isSaved;
    }

    @RequestMapping(value = "/saveComment",method = RequestMethod.POST)
    public @ResponseBody int Comment(Article article, Comment comment, HttpSession session) throws IllegalException {
        int isSaved = blogService.saveComment(article, comment, session);
        return isSaved;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteComment")
    public int deletComment(Long commentId){
        int isDelete = blogService.deletComment(commentId);
        return isDelete;
    }

}
