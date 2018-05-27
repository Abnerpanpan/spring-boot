package com.pan.springboot.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil extends Thread{

    private byte[] file;
    private File targetDayPath;
    private String uploadName;

    public FileUtil(byte[] file, File targetDayPath, String uploadName) {
        this.file = file;
        this.targetDayPath = targetDayPath;
        this.uploadName = uploadName;
    }

    public static void uploadFile(byte[] file, File targetDayPath, String uploadName) throws Exception {
        //以每天创建一个文件夹,一个文件夹内文件过多会性能下降
        //文件名以时间加UUID命名,解决并发时文件名重复
        if(!targetDayPath.exists()){
            targetDayPath.mkdirs();
        }
        BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(targetDayPath +"/"+ uploadName));
        buff.write(file);
        buff.flush();
        buff.close();
    }

    @Override
    public void run() {
        try {
            FileUtil.uploadFile(file, targetDayPath, uploadName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
