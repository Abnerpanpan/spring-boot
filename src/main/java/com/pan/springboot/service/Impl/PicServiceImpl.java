package com.pan.springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pan.springboot.dao.PicMapper;
import com.pan.springboot.entity.Pic;
import com.pan.springboot.entity.PicCustom;
import com.pan.springboot.entity.User;
import com.pan.springboot.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    PicMapper picDao;

    @Override
    public PicCustom savePic(Pic pic, ArrayList<String> pathList, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long coverId = null;
        for(int i=0;i<pathList.size();i++){
            Pic temp = new Pic();
            temp.setUserId(user.getUserId());
            temp.setTimestamp(new Date());
            temp.setPicDescribe(pic.getPicDescribe());
            if(i==0){
                temp.setPath(pathList.get(0));
                temp.setParent(-1L);
                picDao.insertSelective(temp);
                coverId=temp.getPicId();  //已改写insert方法，插入返回主键,如果同时插入多条只返回第一条,即coverID
            }else {
                temp.setPath(pathList.get(i));
                temp.setParent(coverId);
                picDao.insertSelective(temp);
            }
        }
        PicCustom picCustom = new PicCustom();
        picCustom.setPicId(coverId);
        picCustom.setUsername(user.getUsername());
        picCustom.setPicDescribe(pic.getPicDescribe());
        return picCustom;
    }

    @Override
    public PageInfo<PicCustom> selectCover(Integer pageNum) {
        int pageCount=20;
        if(pageNum==null){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,pageCount);
        List<PicCustom> picCustoms = picDao.selectCover();
        PageInfo<PicCustom> PageInfo = new PageInfo<>(picCustoms);
        return PageInfo;
    }
}
