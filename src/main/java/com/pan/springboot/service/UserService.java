package com.pan.springboot.service;


import com.pan.springboot.entity.User;
import com.pan.springboot.exception.IllegalException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;


@Transactional
public interface UserService {
	
	/**
	 * 
	 * @param user
	 * @param session
	 * @param request
	 * @return 返回100：登录成功,返回-1登录失败
	 */
	public int login(User user, HttpSession session, HttpServletRequest request);
	
	public void signUp(User user,HttpSession session);
	
	public int active(String code,HttpSession session) throws IllegalException;
}
