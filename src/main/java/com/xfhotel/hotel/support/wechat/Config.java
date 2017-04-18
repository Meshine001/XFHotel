package com.xfhotel.hotel.support.wechat;

/**
 * 微信相关配置
 * @author Ming
 *
 */
public class Config {
	
	
	public static final String APPID="wxfa31f9e4951f95df";
	public static final String SECRET = "8e6258fafb17861f4d5878060afc6a33";
	
	public static final String REQUEST_CODE_REDIRECT_URI = "http://www.yiyunzn.com/wechat/auth/openId";
//	public static final String REQUEST_CODE_REDIRECT_URI = "http://localhost:8080/hotel/wechat/auth/openId";	
	
	public static final String AUTH_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	public static final String AUTH_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code=CODE&grant_type=authorization_code";
	
	/**
	 * 微信统一下单地址
	 */
	public static final String WX_ORDER_UR = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 微信公众号APPID
	 */
	public static final String MCH_APPID = "wxfa31f9e4951f95df";
	/**
	 * 微信APP端商户号APPID
	 */
	public static final String MCHID = "1458293002";
	/**
	 * 微信APP端商户ID
	 */
	public static final String APP_MCH_APPID = "";
	/**
	 * app_mchid
	 */
	public static final String APP_MCHID = "";
	/**
	 * 业务系统支付回调网址
	 */
	public static final String WX_CALLBACK = "http://www.yiyunzn.com";
	/**
	 * 微信商户后台设置的Key
	 */
	public static final String WX_KEY = "q7w8e9r4t5y6s6a5a2s5x2d8d1d6s3x9";
}
