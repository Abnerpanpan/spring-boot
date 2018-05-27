package com.pan.springboot.service;

import com.pan.springboot.exception.IllegalException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface UploadService {
    ArrayList<String> upLoadImg(MultipartFile[] file, HttpSession session, HttpServletRequest request) throws IllegalException;
}
