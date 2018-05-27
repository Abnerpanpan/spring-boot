package com.pan.springboot.service;

import com.github.pagehelper.PageInfo;
import com.pan.springboot.entity.Secret;
import com.pan.springboot.exception.IllegalException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Transactional
public interface SecretService {

    int SaveSecret(Secret secret, HttpServletRequest request) throws IllegalException;

    PageInfo<Secret> SelectSecrets(Integer pageNum);

}
