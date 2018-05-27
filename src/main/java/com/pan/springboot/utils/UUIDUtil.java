package com.pan.springboot.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String getUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static String getCode(){
		StringBuffer buff = new StringBuffer();
		for(int i=0;i<3;i++){
			String temp=UUID.randomUUID().toString().replaceAll("-", "");
			buff.append(temp);
		}
		return buff.toString();
	}
}
