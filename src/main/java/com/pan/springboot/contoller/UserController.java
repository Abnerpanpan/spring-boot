package com.pan.springboot.contoller;

import com.pan.springboot.entity.User;
import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;




@Controller
public class UserController {
	@Autowired
	UserService userSerive;

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpSession session){
        session.setAttribute("lastUrl",request.getHeader("Referer"));
        return "userpages/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody int Login(User user, HttpSession session, HttpServletRequest request,ModelMap model){
        String lastUrl = (String) session.getAttribute("lastUrl");
        return this.userSerive.login(user, session,request);
    }

    @RequestMapping("/toUserPage")
    public String toUserPage(){
    	return "userpages/userpage";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
    	session.setAttribute("user", null);
    	return "forward:/";
    }

    @RequestMapping("/toSignUp")
    public String toSignup(){
    	return "userpages/signup";
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public @ResponseBody int regist (@Valid User user, BindingResult bindingResult,HttpSession session, Model model) throws IllegalException{
    	if(bindingResult.hasErrors()){
    		List<FieldError> list = bindingResult.getFieldErrors();
            FieldError error = null;
    		HashMap<String, String> errorMap = new HashMap<String, String>();
    		for(int i=0;i<list.size();i++){
    			errorMap.put(list.get(i).getField(),list.get(i).getDefaultMessage());
    		}
    		throw new IllegalException("八戒，你又调皮了~");
    	}
    	userSerive.signUp(user,session);
    	return 100;
    }

    @RequestMapping(value = "/active",method = RequestMethod.GET)
    public String active (String code,HttpSession session,ModelMap model) throws IllegalException{
    	int isActive = userSerive.active(code,session);
    	if(isActive==100){
    		model.addAttribute("msg","激活成功！即将返回主页哦~");
    		return "userpages/active-success";
    	}else{
    		model.addAttribute("msg","验证码失效啦！请返回重新注册哦~");
    		return "userpages/active-fail";
    	}
    }

}
