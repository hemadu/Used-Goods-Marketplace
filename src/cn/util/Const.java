package cn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Const {
	public static String ROOT="/trade/";
	public static String ROOTMANAGE="/trade/manage/";
	public static String USER="user";
	
	public static String getTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String getNo(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
}

