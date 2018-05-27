package com.pan.springboot.dao;

import com.pan.springboot.entity.Collect;

public interface CollectMapper {
    int insert(Collect record);

    int insertSelective(Collect record);
}