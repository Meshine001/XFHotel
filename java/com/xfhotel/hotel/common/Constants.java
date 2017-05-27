package com.xfhotel.hotel.common;

public class Constants {
	
	public static final String Host_Address = "http://www.yiyunzn.xyz/";
	
	public static final String ADMIN_TEL = "18710579465";
	
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
	
	public static final Double YA_JIN = 100D;
	
	
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
	public static final String SMS_INFORM_OVER_PAY = "174009";
	public static final String SMS_INFORM_COMFIRM_ORDER = "174007";
	public static final String SMS_INFORM_COMFIRM_CLEAN_ORDER = "178589";
	
	public static final String SMS_AVAILBEL_TIME_STR = "2分钟";
	public static final Long SMS_AVAILBEL_TIME = Long.valueOf(1000*60*2);
	public static final String SMS_TEMPLATE_REG = "160686";//注册短信验证码模板编号
	public static final String SMS_TEMPLATE_SEND_LOCK = "156532";//发送密码锁短信模板编号
	
	
	
	//支付宝签约账号
	public static final String ALIPAY_PARTNER = "";
	
	
	//微信订单描述
	public static final String WECAT_ORDER_BODY = "青舍都市-房间预订";
	
}
