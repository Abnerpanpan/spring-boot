package com.pan.springboot.dao;

import com.pan.springboot.entity.Secret;

import java.util.List;

public interface SecretMapper {
    int deleteByPrimaryKey(Long secretId);

    int insert(Secret record);

    int insertSelective(Secret record);

    Secret selectByPrimaryKey(Long secretId);

    int updateByPrimaryKeySelective(Secret record);

    int updateByPrimaryKey(Secret record);

    List<Secret> selectByParents(Long parentId);
}