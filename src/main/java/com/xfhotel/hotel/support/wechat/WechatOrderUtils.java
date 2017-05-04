package com.xfhotel.hotel.support.wechat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

/**
 * 微信统一下单工具类
 * 
 * @author Ming
 *
 */
public class WechatOrderUtils {

	/**
	 * 查询订单
	 * @param out_trade_no
	 * @return
	 */
	public static synchronized JSONObject query(String out_trade_no){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(out_trade_no)){
			Log.error("微信支查询订单请求错误：请求参数不足", null);
			result.put("status", "error");
			result.put("msg", "请求参数不足");
			result.put("obj", null);
			return result;
		}
		
		String wx_query = Config.WX_QUERY_URL;
		String appid = Config.MCH_APPID;// 应用ID
		String mchid = Config.MCHID;// 商户ID
		String wx_key = Config.WX_KEY;// 微信商户后台设置的key
		
		if (StringUtils.isBlank(mchid)||StringUtils.isBlank(appid)||StringUtils.isBlank(wx_key)) {
			Log.error("微信支查询订单请求错误：系统配置信息缺失", null);
			result.put("status", "error");
			result.put("msg", "系统配置信息缺失");
			result.put("obj", null);
			return result;
		}
		
		String xml = "<xml>"+
					   "<appid>APPID</appid>"+
					   "<mch_id>MERCHANT</mch_id>"+
					   "<nonce_str>NONCE_STR</nonce_str>"+
					   "<out_trade_no>OUT_TRADE_NO</out_trade_no>"+
					   "<sign>SIGN</sign>"+
			   		"</xml>";

		String nonceStr = getRandomString(32);

		xml = xml.replace("APPID", appid);
		xml = xml.replace("MERCHANT", mchid);
		xml = xml.replace("NONCE_STR", nonceStr);
		xml = xml.replace("OUT_TRADE_NO", out_trade_no);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("mch_id", mchid);
		map.put("nonce_str", nonceStr);
		map.put("out_trade_no", out_trade_no);

		String sign = SignatureUtils.signature(map, wx_key);
		xml = xml.replace("SIGN", sign);
		
		String response = "";
		try {// 注意，此处的httputil一定发送请求的时候一定要注意中文乱码问题，中文乱码问题会导致在客户端加密是正确的，可是微信端返回的是加密错误
			response = HttpUtils.post(wx_query, xml);
		} catch (Exception e) {
			Log.error("微信支查询订单请求错误:http请求失败", e);
			result.put("status", "error");
			result.put("msg", "http请求失败");
			result.put("obj", null);
			return result;
		}

		XStream s = new XStream(new DomDriver());
		s.alias("xml", WechatQuery.class);
		WechatQuery order = (WechatQuery) s.fromXML(response);

		if ("SUCCESS".equals(order.getReturn_code()) && "SUCCESS".equals(order.getResult_code())) {
			
			
		} else {
			Log.error("微信支查询订单请求错误：" + order.getReturn_msg() + order.getErr_code(), null);
			result.put("status", "error");
			result.put("msg", "http请求失败");
			result.put("obj", null);
			return result;
		}
		
		Log.info("微信支查询订单请求成功", null);
		result.put("status", "success");
		result.put("msg", "查询成功");
		JSONObject back = new JSONObject();
		if("SUCCESS".equals(order.getTrade_state())){
			back.put("trade_type", order.getTrade_type());
			back.put("trade_state", order.getTrade_state());
			back.put("bank_type", order.getBank_type());
			back.put("total_fee", order.getTotal_fee());
			back.put("transaction_id", order.getTransaction_id());
			back.put("time_end", order.getTime_end());
			back.put("out_trade_no", order.getOut_trade_no());
			back.put("trade_state_desc", order.getTrade_state_desc());
			result.put("obj", back);
			return result;
		}else{
			back.put("out_trade_no", order.getOut_trade_no());
			back.put("trade_state_desc", order.getTrade_state_desc());
			result.put("obj", back);
			return result;
		}

	}
	
	
	/**
	 * 申请退款
	 * @param out_trade_no
	 * @param out_refund_no
	 * @param total_fee
	 * @param refund_fee
	 * @return
	 */
	public static synchronized JSONObject refund(String out_trade_no, String out_refund_no, String total_fee,
			String refund_fee) {
		JSONObject result = new JSONObject();

		if (StringUtils.isBlank(out_trade_no) || StringUtils.isBlank(out_refund_no) || StringUtils.isBlank(total_fee)
				|| StringUtils.isBlank(refund_fee)) {
			Log.error("微信支申请退款请求错误：请求参数不足", null);
			result.put("status", "error");
			result.put("msg", "请求参数不足");
			result.put("obj", null);
			return result;
		}

		double totalAmount = 0;// 对应微信支付的真实数目
		double refundAmout = 0;
		try {// 进行格式转换异常获取，保证数目正确
			totalAmount = Double.parseDouble(total_fee) * 100;
			refundAmout = Double.parseDouble(refund_fee) * 100;
		} catch (Exception e) {
			Log.error("微信支申请退款请求错误：请求金额格式错误", e);
			result.put("status", "error");
			result.put("msg", "请求金额格式错误");
			result.put("obj", null);
			return result;
		}

		if (totalAmount == 0 || refundAmout == 0) {
			Log.error("微信支申请退款请求错误：请求金额不能为0", null);
			result.put("status", "error");
			result.put("msg", "请求金额不能为0");
			result.put("obj", null);
			return result;
		}

		String wx_refund = Config.WX_REFUND_URL;
		String appid = Config.MCH_APPID;// 应用ID
		String mchid = Config.MCHID;// 商户ID
		String wx_key = Config.WX_KEY;// 微信商户后台设置的key

		if (StringUtils.isBlank(mchid)||StringUtils.isBlank(appid)||StringUtils.isBlank(wx_key)) {
			Log.error("微信支申请退款请求错误：系统配置信息缺失", null);
			result.put("status", "error");
			result.put("msg", "系统配置信息缺失");
			result.put("obj", null);
			return result;
		}

		// 发送报文模板,其中部分字段是可选字段
		String xml = "" + "<xml>" + "<appid>APPID</appid>" + // 应用ID
				"<mch_id>MERCHANT</mch_id>" + // 微信给的商户ID
				"<nonce_str>NONCE_STR</nonce_str>" + // 32位随机字符串
				"<out_trade_no>OUT_TRADE_NO</out_trade_no>" + // 商户侧传给微信的订单号
				"<out_refund_no>OUT_REFUND_NO</out_refund_no>" + // 商户系统内部的退款单号
				"<total_fee>TOTAL_FEE</total_fee>" + // 订单总金额
				"<refund_fee>REFUND_FEE</refund_fee>" + // 退款总金额
				"<op_user_id>OP_USER_ID</op_user_id>" + // 操作员帐号, 默认为商户号
				"<sign>SIGN</sign>" + // 加密字符串
				"</xml>";

		String nonceStr = getRandomString(32);

		xml = xml.replace("APPID", appid);
		xml = xml.replace("MERCHANT", mchid);
		xml = xml.replace("NONCE_STR", nonceStr);
		xml = xml.replace("OUT_TRADE_NO", out_trade_no);
		xml = xml.replace("OUT_REFUND_NO", out_refund_no);
		xml = xml.replace("TOTAL_FEE", "" + (int) totalAmount);
		xml = xml.replace("REFUND_FEE", "" + (int) refundAmout);
		xml = xml.replace("OP_USER_ID", mchid);

		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("mch_id", mchid);
		map.put("nonce_str", nonceStr);
		map.put("out_trade_no", out_trade_no);
		map.put("out_refund_no", out_refund_no);
		map.put("total_fee", "" + (int) totalAmount);
		map.put("refund_fee", "" + (int) refundAmout);
		map.put("op_user_id", mchid);

		String sign = SignatureUtils.signature(map, wx_key);
		xml = xml.replace("SIGN", sign);

		String response = "";
		try {// 注意，此处的httputil一定发送请求的时候一定要注意中文乱码问题，中文乱码问题会导致在客户端加密是正确的，可是微信端返回的是加密错误
			response = ClientCustomSSL.post(wx_refund, xml);
		} catch (Exception e) {
			Log.error("微信支申请退款请求错误:http请求失败", e);
			result.put("status", "error");
			result.put("msg", "http请求失败");
			result.put("obj", null);
			return result;
		}

		XStream s = new XStream(new DomDriver());
		s.alias("xml", WechatRefund.class);
		WechatRefund refund = (WechatRefund) s.fromXML(response);
		System.out.println(refund);
		if ("SUCCESS".equals(refund.getReturn_code()) && "SUCCESS".equals(refund.getResult_code())) {
			Log.info("微信支申请退款请求成功：" + refund.getRefund_id(), null);
		} else {
			Log.error("微信支申请退款请求错误：" + refund.getErr_code()+","+refund.getErr_code_des(), null);
			result.put("status", "error");
			result.put("msg", refund.getErr_code_des());
			return result;
		}

		HashMap<String, String> back = new HashMap<String, String>();
		back.put("appid", refund.getAppid());
		back.put("mch_id", refund.getMch_id());
		back.put("out_trade_no", refund.getOut_trade_no());
		back.put("out_refund_no", refund.getOut_refund_no());
		back.put("out_refund_no", refund.getOut_refund_no());
		back.put("refund_fee", refund.getRefund_fee());
		back.put("total_fee", refund.getTotal_fee());

		result.put("status", "success");
		result.put("msg", "下单成功");
		result.put("obj", back);
		return result;
	}

	/**
	 * 统一下单
	 * 
	 * @param detail
	 *            订单详情，必填
	 * @param desc
	 *            商品或订单描述，必填
	 * @param openid
	 *            公众号调起时需要的OPENID，选填，不填传“”
	 * @param ip
	 *            下订单时的IP，必填
	 * @param goodSn
	 *            业务系统商品编号，必填
	 * @param orderSn
	 *            业务系统订单编号，必填
	 * @param amount
	 *            金额，必填
	 * @param type
	 *            支付类型，分为三种，JSAPI表示公众号调起的支付，NATIVE用于PC端网页调起的扫码支付，APP用于APP端调起的支付
	 * @return 返回对象中封装了网页和APP调起支付控件需要的参数，根据不同的支付类型，有不同的返回参数
	 */
	public static synchronized JSONObject createOrder(String detail, String desc, String openid, String ip,
			String goodSn, String orderSn, String amount, String type) {

		JSONObject result = new JSONObject();
		// 1、参数校验
		if (StringUtils.isBlank(detail) || StringUtils.isBlank(desc) || StringUtils.isBlank(ip)
				|| StringUtils.isBlank(goodSn) || StringUtils.isBlank(orderSn) || StringUtils.isBlank(amount)
				|| StringUtils.isBlank(type)) {
			Log.error("微信支付统一下单请求错误：请求参数不足", null);
			result.put("status", "error");
			result.put("msg", "请求参数不足");
			result.put("obj", null);
			return result;
		}

		double relAmount = 0;// 对应微信支付的真实数目
		try {// 进行格式转换异常获取，保证数目正确
			relAmount = Double.parseDouble(amount) * 100;
		} catch (Exception e) {
			Log.error("微信支付统一下单请求错误：请求金额格式错误", e);
			result.put("status", "error");
			result.put("msg", "请求金额格式错误");
			result.put("obj", null);
			return result;
		}

		if (relAmount == 0) {// 微信支付的支付金额必须为大于0的int类型，单位为分
			Log.error("微信支付统一下单请求错误：请求金额不能为0", null);
			result.put("status", "error");
			result.put("msg", "请求金额不能为0");
			result.put("obj", null);
			return result;
		}

		if (!("JSAPI".equalsIgnoreCase(type) || "NATIVE".equalsIgnoreCase(type) || "APP".equalsIgnoreCase(type))) {
			Log.error("微信支付统一下单请求错误：支付类型为空", null);
			result.put("status", "error");
			result.put("msg", "支付类型为空");
			result.put("obj", null);
			return result;
		}

		/* 公众号调起微信支付的时候，必须要有openID */
		if ("JSAPI".equalsIgnoreCase(type) && StringUtils.isBlank(openid)) {
			Log.error("微信支付统一下单请求错误：请求参数不足", null);
			result.put("status", "error");
			result.put("msg", "请求参数不足");
			result.put("obj", null);
			return result;
		}

		// 2、获取系统配置信息
		String wx_order = Config.WX_ORDER_URL;// 获取统一下单接口地址
		String mchappid = Config.MCH_APPID;// 商户appid
		String mchid = Config.MCHID;// 商户ID
		String wx_callback = Config.WX_CALLBACK;// 获取微信支付回调接口
		String wx_key = Config.WX_KEY;// 微信商户后台设置的key
		String app_mchid = Config.APP_MCHID;// APP调起微信支付的商户ID
		String app_mchappid = Config.APP_MCH_APPID;// APP调起微信的APPID

		if (StringUtils.isBlank(wx_order) || StringUtils.isBlank(mchappid) || StringUtils.isBlank(mchid)
				|| StringUtils.isBlank(wx_callback)) {
			Log.error("微信支付统一下单请求错误：系统配置信息缺失", null);
			result.put("status", "error");
			result.put("msg", "系统配置信息缺失");
			result.put("obj", null);
			return result;
		}

		// 发送报文模板,其中部分字段是可选字段
		String xml = "" + "<xml>" + "<appid>APPID</appid>" + // 公众号ID
				"<device_info>WEB</device_info>" + // 设备信息
				"<detail>DETAIL</detail>" + // 商品详情
				"<body>BODY</body>" + // 商品描述
				"<mch_id>MERCHANT</mch_id>" + // 微信给的商户ID
				"<nonce_str>NONCE_STR</nonce_str>" + // 32位随机字符串
				"<notify_url><![CDATA[URL_TO]]></notify_url>" + // 信息通知页面
				"<openid>UserFrom</openid>" + // 支付的用户ID
				"<fee_type>CNY</fee_type>" + // 支付货币，不改
				"<spbill_create_ip>IP</spbill_create_ip>" + // 用户IP
				"<time_start>START</time_start>" + // 订单开始时间
				"<time_expire>STOP</time_expire>" + // 订单结束时间
				"<goods_tag>WXG</goods_tag>" + // 商品标记，不改
				"<product_id>GOODID</product_id>" + // 商品ID
				"<limit_pay>no_credit</limit_pay>" + // 支付范围，默认不支持信用卡支付，不改
				"<out_trade_no>PAY_NO</out_trade_no>" + // 商城生成的订单号
				"<total_fee>TOTAL</total_fee>" + // 总金额
				"<trade_type>TYPE</trade_type>" + // 交易类型，JSAPI，NATIVE，APP，WAP
				"<sign>SIGN</sign>" + // 加密字符串
				"</xml>";

		// 生成订单起始时间，订单7天内有效
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String start_time = df.format(new Date());
		String stop_time = df.format(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);

		// 随机字符串
		String nonceStr = getRandomString(32);
		// 3、xml数据封装

		// APP调起的时候，可能和公众号调起的商户号是不同的，所以需要分开设置
		if ("APP".equalsIgnoreCase(type)) {
			xml = xml.replace("MERCHANT", app_mchid);
			xml = xml.replace("APPID", app_mchappid);
		} else {
			xml = xml.replace("MERCHANT", mchid);
			xml = xml.replace("APPID", mchappid);
		}

		xml = xml.replace("NONCE_STR", nonceStr);

		xml = xml.replace("DETAIL", detail);
		xml = xml.replace("BODY", desc);
		xml = xml.replace("URL_TO", wx_callback);
		xml = xml.replace("IP", ip);
		xml = xml.replace("START", start_time);
		xml = xml.replace("STOP", stop_time);
		xml = xml.replace("GOODID", goodSn);
		xml = xml.replace("PAY_NO", orderSn);
		xml = xml.replace("TOTAL", (int) relAmount + "");
		xml = xml.replace("TYPE", type);
		if ("NATIVE".equalsIgnoreCase(type) || "APP".equalsIgnoreCase(type)) {
			xml = xml.replace("<openid>UserFrom</openid>", openid);
		} else {
			xml = xml.replace("UserFrom", openid);
		}
		// 4、加密
		Map<String, String> map = new HashMap<String, String>();
		map.put("device_info", "WEB");
		map.put("detail", detail);
		map.put("body", desc);
		if ("APP".equalsIgnoreCase(type)) {
			map.put("mch_id", app_mchid);
			map.put("appid", app_mchappid);
		} else {

			map.put("mch_id", mchid);
			map.put("appid", mchappid);
		}

		map.put("nonce_str", nonceStr);
		map.put("notify_url", wx_callback);
		map.put("fee_type", "CNY");
		map.put("spbill_create_ip", ip);
		map.put("time_start", start_time);
		map.put("time_expire", stop_time);
		map.put("goods_tag", "WXG");
		map.put("product_id", goodSn);
		map.put("limit_pay", "no_credit");
		map.put("out_trade_no", orderSn);
		map.put("total_fee", (int) relAmount + "");
		map.put("trade_type", type);
		if (("JSAPI".equalsIgnoreCase(type))) {
			map.put("openid", openid);
		}

		String sign = SignatureUtils.signature(map, wx_key);
		xml = xml.replace("SIGN", sign);
		
//		System.out.println(xml);
		
		// 5、请求
		String response = "";
		try {// 注意，此处的httputil一定发送请求的时候一定要注意中文乱码问题，中文乱码问题会导致在客户端加密是正确的，可是微信端返回的是加密错误
			response = HttpUtils.post(wx_order, xml);
		} catch (Exception e) {
			Log.error("微信支付统一下单失败:http请求失败", e);
			result.put("status", "error");
			result.put("msg", "http请求失败");
			result.put("obj", null);
			return result;
		}

		// 6、处理请求结果
		XStream s = new XStream(new DomDriver());
		s.alias("xml", WechatOrder.class);
		WechatOrder order = (WechatOrder) s.fromXML(response);

		if ("SUCCESS".equals(order.getReturn_code()) && "SUCCESS".equals(order.getResult_code())) {
			Log.info("微信支付统一下单请求成功：" + order.getPrepay_id(), null);
		} else {
			Log.error("微信支付统一下单请求错误：" + order.getReturn_msg() + order.getErr_code(), null);
			result.put("status", "error");
			result.put("msg", "http请求失败");
			result.put("obj", null);
			return result;
		}

		HashMap<String, String> back = new HashMap<String, String>();

		// 生成客户端调时需要的信息对象
		if ("JSAPI".equalsIgnoreCase(type)) {
			// 网页调起的时候
			String time = Long.toString(System.currentTimeMillis());
			back.put("appId", mchappid);
			back.put("timeStamp", time);
			back.put("nonceStr", nonceStr);
			back.put("package", "prepay_id=" + order.getPrepay_id());
			back.put("signType", "MD5");
			String sign2 = SignatureUtils.signature(back, wx_key);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("appId", mchappid);
			jsonObject.put("timeStamp", time);
			jsonObject.put("nonceStr", nonceStr);
			jsonObject.put("package", "prepay_id=" + order.getPrepay_id());
			jsonObject.put("signType", "MD5");
			jsonObject.put("paySign", sign2);

			result.put("status", "success");
			result.put("msg", "下单成功");
			result.put("obj", jsonObject);
			System.out.println("公共号下单成功:"+result);
			return result;

		} else if ("NATIVE".equalsIgnoreCase(type)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("url", order.getCode_url());
			result.put("status", "success");
			result.put("msg", "下单成功");
			result.put("obj", jsonObject);
			return result;

		} else if ("APP".equalsIgnoreCase(type)) {

			// APP调起的时候,请注意，安卓端不能用驼峰法，所有的key必须使用小写
			String time = Long.toString(System.currentTimeMillis());
			back.put("appid", app_mchappid);
			back.put("timestamp", time);
			back.put("partnerid", app_mchid);
			back.put("noncestr", nonceStr);
			back.put("prepayid", order.getPrepay_id());
			back.put("package", "Sign=WXPay");
			String sign2 = SignatureUtils.signature(back, wx_key);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("appid", app_mchappid);
			jsonObject.put("timestamp", time);
			jsonObject.put("partnerid", app_mchid);
			jsonObject.put("noncestr", nonceStr);
			jsonObject.put("prepayid", order.getPrepay_id());
			jsonObject.put("package", "Sign=WXPay");
			jsonObject.put("sign", sign2);
			result.put("status", "success");
			result.put("msg", "下单成功");
			result.put("obj", jsonObject);
			return result;

		}
		return result;
	}

	// public static synchronized JSONObject

	@Deprecated
	/**
	 * 获得客户端IP
	 * (不好用！)
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for");
		 System.out.println(request.getHeader("Proxy-Client-IP"));
		 System.out.println(request.getHeader("WL-Proxy-Client-IP"));
		 System.out.println(request.getRemoteAddr());
		  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		  {
		    ip = request.getHeader("Proxy-Client-IP");
		  }
		  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		  {
		    ip = request.getHeader("WL-Proxy-Client-IP");
		  }
		  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		  {
		    ip = request.getRemoteAddr();
		  }
		  return ip;
	}

	/**
	 * 随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
//		System.out.println(getRandomString(32));
		// String detail = "支付测试";
		// String desc = "休闲房-2011室-1晚";
		// String openid = "1";
		// String ip = "192.12.12.22";
		// String goodSn = "1";
		// String orderSn = "12";
		// String amount = "2999";
		// String type = "JSAPI";
		// JSONObject result = WechatOrderUtils.createOrder(detail, desc,
		// openid, ip, goodSn, orderSn, amount, type);
		// System.out.println(result);
		
//		JSONObject result = WechatOrderUtils.refund("123213", "21323", "322.11", "22.2");
//		JSONObject result1 = WechatOrderUtils.query("2017042500002");
		JSONObject result = WechatOrderUtils.refund("2017042500002", "2017042500001", "0.01", "0.01");
//		System.out.println(result1);
		System.out.println(result);
	}

}
