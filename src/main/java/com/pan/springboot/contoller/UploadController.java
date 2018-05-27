package com.pan.springboot.contoller;

import com.pan.springboot.entity.Pic;
import com.pan.springboot.entity.PicCustom;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.PicService;
import com.pan.springboot.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class UploadController {

    @Autowired
    UploadService uploadService;
    @Autowired
    PicService picService;
    //处理文件上传
    @ResponseBody
    @RequestMapping(value = "/uploadImage")
    public HashMap<String, Object> upLoad(HttpSession session, @RequestParam("filedata") MultipartFile[] file, HttpServletRequest request ,Pic pic) throws IOException, IllegalException {
        ArrayList<String> pathList = uploadService.upLoadImg(file, session, request);
        //整合博客上传图片和修图区上传图片
        //pic为null时：博客上传图
        //pic不为null：修图区上传图
        HashMap<String, Object> map = new HashMap<>();
        if("http://www.abnerpan.com/toPic".equals(request.getHeader("Referer")) || "https://www.abnerpan.com/toPic".equals(request.getHeader("Referer"))){
            PicCustom coverPic = picService.savePic(pic, pathList, session);
            coverPic.setPath(pathList.get(0));
            map.put("pic",coverPic);
        }
        map.put("errno",0);
        map.put("data",pathList);
        return map;
    }

}

