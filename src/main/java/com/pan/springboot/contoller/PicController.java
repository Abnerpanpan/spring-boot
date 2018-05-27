package com.pan.springboot.contoller;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.PicCustom;
import com.pan.springboot.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PicController {

    @Autowired
    PicService picService;

    @RequestMapping(value = "/toPic",method = RequestMethod.GET)
    public String toPic(ModelMap modelMap,Integer pageNum){
        PageInfo<PicCustom> pageInfo = picService.selectCover(pageNum);
        modelMap.addAttribute("dataList",pageInfo);
        return "picpages/pic";
    }

    @RequestMapping("/test")
    public String toTest(){
        return"test";
    }

}
