package com.sinaapp.zhangboss.helper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTransfer {

	public static Date getDateByString(String strDate) throws ParseException{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(strDate);
		return date;
	}
}
