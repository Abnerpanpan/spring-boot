package com.pan.springboot.contoller;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.Secret;
import com.pan.springboot.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    SecretService secretService;

    @RequestMapping("/")
    public String Home(ModelMap model){
        PageInfo<Secret> secretPageInfo = secretService.SelectSecrets(1);
        secretPageInfo.setList(secretPageInfo.getList().subList(0,5));
        model.addAttribute("dataList",secretPageInfo);
        return "home";
    }




}
