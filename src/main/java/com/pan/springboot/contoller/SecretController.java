package com.pan.springboot.contoller;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.Secret;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SecretController {

    @Autowired
    SecretService secretService;

    @RequestMapping(value = "/toSecret",method = RequestMethod.GET)
    public String toSecret(ModelMap modle,Integer pageNum){
        PageInfo<Secret> secretPageInfo = secretService.SelectSecrets(pageNum);
        modle.addAttribute("dataList",secretPageInfo);
        return "secretpages/secret";
    }

    @ResponseBody
    @RequestMapping(value = "/saveSecret",method = RequestMethod.POST)
    public int saveSecret(@Valid Secret secret, HttpServletRequest request) throws IllegalException {
        return secretService.SaveSecret(secret,request);
    }
}
