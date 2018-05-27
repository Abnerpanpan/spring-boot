package com.pan.springboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pan.springboot.dao.SecretMapper;
import com.pan.springboot.entity.Secret;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.SecretService;
import com.pan.springboot.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class SecretServiceImpl implements SecretService {
    @Autowired
    SecretMapper secretDao;

    @Override
    public int SaveSecret(Secret secret, HttpServletRequest request) throws IllegalException {
        if("".equals(secret.getContent().trim())||null==secret.getContent()){
            throw new IllegalException("八戒,写点什么东西吧");
        }
        if(secret.getParentId()==null){
            secret.setParentId(-1L);
        }
        secret.setIp(IpUtil.getIpAddr(request));
        secret.setTimestamp(new Date());
        int isSaved = secretDao.insertSelective(secret);
        if(isSaved>0){
            return isSaved;
        }else {
            return -100;
        }
    }

    @Override
    public PageInfo<Secret> SelectSecrets(Integer pageNum) {
        if(pageNum==null){
            pageNum=1;
        }
        PageHelper.startPage(pageNum,10);
        List<Secret> secrets = secretDao.selectByParents(-1L);
        for(Secret secret:secrets){
            List<Secret> commentList = secretDao.selectByParents(secret.getSecretId());
            secret.setComments(commentList);
        }
        PageInfo<Secret> secretPageInfo = new PageInfo<>(secrets);
        return secretPageInfo;
    }

}
