package com.pan.springboot.dao;

import com.pan.springboot.entity.Pic;
import com.pan.springboot.entity.PicCustom;

import java.util.List;

public interface PicMapper {
    int deleteByPrimaryKey(Long picId);

    int insert(Pic record);

    Long insertSelective(Pic record);

    Pic selectByPrimaryKey(Long picId);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);

    List<PicCustom> selectCover();
}