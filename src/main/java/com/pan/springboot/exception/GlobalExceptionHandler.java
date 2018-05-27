package com.pan.springboot.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value={Exception.class})
    public ModelAndView commentErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("exception",e);
        model.addObject("url", req.getRequestURL());
        model.setViewName("error");
        return model;
    }
}
