package com.pan.springboot.dao;

import com.pan.springboot.entity.Selection;

import java.util.List;

public interface SelectionMapper {
    int deleteByPrimaryKey(Long selectionId);

    int insert(Selection record);

    int insertSelective(Selection record);

    Selection selectByPrimaryKey(Long selectionId);

    int updateByPrimaryKeySelective(Selection record);

    int updateByPrimaryKey(Selection record);

    List<Selection> find();
}