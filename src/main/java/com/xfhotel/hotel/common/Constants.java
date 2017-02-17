package com.xfhotel.hotel.common;

public class Constants {
	
	public static final String Host_Address = "http://localhost:8080/hotel/";
	
	public static final String ADMIN_SESSION_ATTR = "admin-session";
	
	
	public static final String DEFAULT_AVATAR = "avatar_default.jpg";
	
	public static final String PAGE = "page";
	
	public static final String PAGE_DETAILS = "details";
	public static final String PAGE_RESERVATION = "reservation";
	public static final String PAGE_CHANGE_PWD = "change-pwd";
	
	public static final int MESSAGE_ERR_CODE = 0;
	public static final int MESSAGE_SUCCESS_CODE = 1;
	
	public static final int price_scope[] = new int[]{600,1000,1400,1800,2200};
	public static final Long EFFECTIVE_ORDER_TIME_DURING = 1000*60*30L;
	
	public static final String TYPE_HOTEL = "酒店式公寓";
	public static final String TYPE_PLAY_ROOM = "休闲式公寓";
	public static final String TYPE_ALL = "不限";
	
	public static final int pagesize = 10;
}
