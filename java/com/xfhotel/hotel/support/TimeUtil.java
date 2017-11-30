package com.xfhotel.hotel.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeUtil {
	
	/**
	 * 获取某两日期之间的日期
	 * @param stime
	 * @param etime
	 * @return
	 */
	public static  List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (ParseException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
        }

   long betweendays=(long) ((eDate.getTime()-sdate.getTime())/(1000 * 60 * 60 *24)+0.5);//天数间隔
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
//              System.out.println(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取某日期后几个月的日期
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getDatePlusMonth(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		Date newDate = calendar.getTime();
		return newDate;
	}

	/**
	 * 获取几个月后的日期
	 * 
	 * @param month
	 * @return
	 */
	public static Date getCurrentDatePlusMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, month);
		Date newDate = calendar.getTime();
		return newDate;
	}

	/**
	 * 获取某时间段所有日期
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List lDate = new ArrayList();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	public static String getDateStr(Long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(date));
	}

	public static Long getDateLong(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long l = 0L;
		try {
			l = sdf.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	public static Long getDateLong(String date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
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
	public static Long getCurrentDateLong() {
		Date d = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append(DateFormat.getDateInstance().format(d));
		// System.out.println(sb.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long l = 0L;
		try {
			l = sdf.parse(sb.toString()).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * 获取某月所有日期
	 * 
	 * @param month
	 * @return
	 */
	public static List<Date> getAllDateInMonth(int year, int month) {
		List<Date> dates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dates.add(df.parse(df.format(cal.getTime())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < maxDay; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i + 1);
			try {
				dates.add(df.parse(df.format(cal.getTime())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dates;
	}
	
	public static String getFirstDay(String str1) throws ParseException {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = df.parse(str1);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.DATE, 1);
	    Date theDate = calendar.getTime();
	    String s = df.format(theDate);
	    StringBuffer str = new StringBuffer().append(s).append(" 00:00:00");
	    return str.toString();

	}
	public static String getLastDay(String str1) throws ParseException {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = Calendar.getInstance();
	    Date date = df.parse(str1);
	    calendar.setTime(date);
	    calendar.add(Calendar.MONTH, 1);    //加一个月
	    calendar.set(Calendar.DATE, 1);        //设置为该月第一天
	    calendar.add(Calendar.DATE, -1);
	    Date theDate = calendar.getTime();
	    String s = df.format(theDate);
	    StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
	    return str.toString();

	}

	public static Map<String, String> getFirstday_Lastday_Month(Date date) {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MONTH, -1);
	    Date theDate = calendar.getTime();
	    
	    //上个月第一天
	    GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	    gcLast.setTime(theDate);
	    gcLast.set(Calendar.DAY_OF_MONTH, 1);
	    String day_first = df.format(gcLast.getTime());
	    StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
	    day_first = str.toString();

	    //上个月最后一天
	    calendar.add(Calendar.MONTH, 1);    //加一个月
	    calendar.set(Calendar.DATE, 1);        //设置为该月第一天
	    calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
	    String day_last = df.format(calendar.getTime());
	    StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
	    day_last = endStr.toString();

	    Map<String, String> map = new HashMap<String, String>();
	    map.put("first", day_first);
	    map.put("last", day_last);
	    return map;
	}
	
	public static void main(String[] args) throws ParseException {
//		// TODO Auto-generated method stub
//		Date d = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		for (Date dd : TimeUtil.getAllDateInMonth(2017, d.getMonth())) {
//			System.out.println(df.format(dd));
//		}

//		System.out.println(getBetweenDays("2017-03-16", "2017-03-30"));

	}

	public static Long getDateLong(long time) {
		// TODO Auto-generated method stub
		return null;
	}
}
