package com.pan.springboot.service.Impl;

import com.pan.springboot.exception.IllegalException;
import com.pan.springboot.service.UploadService;
import com.pan.springboot.utils.FileUtil;
import com.pan.springboot.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public ArrayList<String> upLoadImg(MultipartFile[] file, HttpSession session, HttpServletRequest request) throws IllegalException {
        if(file.length<6){
            if(session.getAttribute("user")!=null){
                String webPath="http://images.abnerpan.com/"; //域名
                String realRootFile="/static/upload/";            //上传真实文件夹
                String returnRootFile="upload/";            //上传真实文件夹
                /*String rootFile="imgupload/";            //上传文件夹
                String filePath = request.getSession().getServletContext().getRealPath("/");*/
                ArrayList<String> imgList = new ArrayList<>();
                for(int i=0;i<file.length;i++){
                    String suffix =file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
                    String UUID= UUIDUtil.getUuid().substring(22);
                    String uploadName=new SimpleDateFormat("yyMMddHHmmss").format(new Date())+UUID+suffix;
                    String dayPath=uploadName.substring(0,6)+"/";
                    File targetDayPath = new File(realRootFile+dayPath);
                    try {
                        FileUtil thread = new FileUtil(file[i].getBytes(), targetDayPath, uploadName);
                        thread.start();
                        imgList.add(webPath+returnRootFile+dayPath+ uploadName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return imgList;
            }
        }
        throw new IllegalException("八戒,你又调皮了~");
    }
}
