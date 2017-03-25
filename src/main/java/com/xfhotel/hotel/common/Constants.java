package com.xfhotel.hotel.common;

public class Constants {
	
	public static final String Host_Address = "http://localhost:8080/hotel/";
	
	public static final String ADMIN_SESSION_ATTR = "admin-session";
	public static final String CUSTOMER_SESSION_ATTR = "c";
	
	
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
	
	//******************************注释*********************************************
	//*初始化服务器地址和端口                                                       *
	//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
	//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
	//*******************************************************************************
	public static final String SMS_URL = "app.cloopen.com";
	public static final String SMS_PORT = "8883";
	//******************************注释*********************************************
	//*初始化应用ID                                                                 *
	//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
	//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
	//*******************************************************************************
	public static final String SMS_AUTH_SID = "8a216da85a362949015a7e315f941723";
	public static final String SMS_AUTH_TOKEN = "68a5052cca464b099e7e1407d7418712";
	public static final String SMS_APP_ID = "8a216da85a362949015a7e315fb51724";
	public static final String SMS_VALIDATE_REGISTER = "156526";
	public static final String SMS_VALIDATE_MODIFY_PASSWORD = "156527";
	public static final String SMS_INFORM_LOCK_CODE = "156532";
	
	public static final String LOCK_TEST_BASE_URL = "http://test.ops.huohetech.com:80";
	public static final String LOCK_BASE_URL = "http://ops.huohetech.com:80";
	
	public static final String LOCK_DES_KEY = "11111111";
	public static final String LOCK_ACCOUNT = "13072983237";
	public static final String LOCK_PASSWORD = "123321";
	
	public static final String LOCK_MSG_SUCCESS = "HH0000";
	public static final String LOCK_MSG_TOKEN_OUT_OF_DATE = "OPS04110";
	public static final String LOCK_MSG_TOKEN_INVALIDATE = "OPS04100";
	public static final String LOCK_MSG_TOKEN_NOT_EXIST = "OPS00003";
	
	//支付宝签约账号
	public static final String ALIPAY_PARTNER = "";
}
