package com.pan.springboot.service.Impl;

import com.pan.springboot.dao.UserMapper;
import com.pan.springboot.entity.User;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.UserService;
import com.pan.springboot.utils.IpUtil;
import com.pan.springboot.utils.MailUtil;
import com.pan.springboot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	private UserMapper userDao;

	@Override
	public int login(User user, HttpSession session, HttpServletRequest request) {
		User exitUser = userDao.findByuserNameAndPwd(user);
		if(exitUser!=null){
			String ipAddr = IpUtil.getIpAddr(request);
			exitUser.setUserIp(ipAddr);
			exitUser.setLoginTime(new Date());
			userDao.updateByPrimaryKeySelective(exitUser);
			session.setAttribute("user", exitUser);
			return 100;
		}
		return -1;
	}

	public void signUp(User user,HttpSession session) {
		user.setRegistTime(new Date());
		String code=UUIDUtil.getCode();
		user.setUstaus(code);
		MailUtil.sendMail(user,code);
		userDao.insertSelective(user);
	}

	public int active(String code,HttpSession session) throws IllegalException {
		User existUser = userDao.findByUstaus(code);
		if(existUser!=null){
			Long registTime = existUser.getRegistTime().getTime();
			Long now=new Date().getTime();
			if((now-registTime)>12*60*60*1000){
				return -1;
			}
			existUser.setUstaus(User.USER_STAUS_CAN);
			userDao.updateByPrimaryKeySelective(existUser);
			session.setAttribute("user", existUser);
			return 100;
		}else{
			throw new IllegalException("八戒，你是逃不出为师手掌心的~");
		}
	}

}
