package com.pan.springboot.dao;

import com.pan.springboot.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByuserNameAndPwd(User user);
    
    User findByUstaus(String code);
}