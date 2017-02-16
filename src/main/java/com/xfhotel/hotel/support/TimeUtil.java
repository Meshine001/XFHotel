package com.xfhotel.hotel.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static String getDateStr(Long date){
		SimpleDateFormat  sdf = new SimpleDateFormat( "yyyy-MM-dd");
		return sdf.format(new Date(date));
	}
	
	public static Long getDateLong(String date){
		SimpleDateFormat  sdf = new SimpleDateFormat( "yyyy-MM-dd");
		Long l = 0L;
		try {
			l = sdf.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	@SuppressWarnings("deprecation")
	public static Long getCurrentDateLong(){
		Date d = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormat.getDateInstance().format(d));
		//System.out.println(sb.toString());
		SimpleDateFormat  sdf = new SimpleDateFormat( "yyyy-MM-dd");
		Long l = 0L;
		try {
			l = sdf.parse(sb.toString()).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				System.out.println(TimeUtil.getCurrentDateLong());
	}
}
