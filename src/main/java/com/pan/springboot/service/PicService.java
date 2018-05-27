package com.pan.springboot.service;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.Pic;
import com.pan.springboot.entity.PicCustom;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface PicService {

    PicCustom savePic(Pic pic, ArrayList<String> pathList, HttpSession session);

    PageInfo<PicCustom> selectCover(Integer pageNum);
}
