package com.xfhotel.hotel.support.pay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 用于微信支付 传输方式 为保证交易安全性，采用HTTPS传输 提交方式 采用POST方法提交 数据格式 提交和返回数据都为XML格式，根节点名为xml
 * 字符编码 统一采用UTF-8字符编码 签名算法 MD5，后续会兼容SHA1、SHA256、HMAC等。 签名要求
 * 请求和接收数据均需要校验签名，详细方法请参考安全规范-签名算法 证书要求 调用申请退款、撤销订单接口需要商户证书 判断逻辑
 * 先判断协议字段返回，再判断业务返回，最后判断交易状态
 * 
 * @author Ming
 *
 */
public class WechatPaySDK {

	public static final String SIGN_KEY = "192006250b4c09247ec02edce69f6a2d";
	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appId）
	 */
	public static final String APP_ID = "wxfa31f9e4951f95df";
	
	public static final String API_KEY = "q7w8e9r4t5y6s6a5a2s5x2d8d1d6s3x9";
	
	public static final String APP_SECRET = "8e6258fafb17861f4d5878060afc6a33";
	/**
	 * 微信支付分配的商户号
	 */
	public static final String MCH_ID = "1458293002";

	/**
	 * 用户标识 trade_type=JSAPI时（即公众号支付），此参数必传， 此参数为微信用户在商户对应appid下的唯一标识。
	 */
	public static final String OPEN_ID = "";
	/**
	 * 签名类型
	 */
	public static final String SIGN_TYPE_MD5 = "MD5";
	public static final String SIGN_TYPE_HMAC_SHA256 = "HMAC-SHA256";
	/**
	 * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
	 */
	public static final String NOTIFY_URL = "http://www.yiyunzn.com/wecat/pay/push";
	/**
	 * 交易类型
	 */
	public static final String TRADE_TYPE_JSAPI = "JSAPI";
	public static final String TRADE_TYPE_NATIVE = "NATIVE";
	public static final String TRADE_TYPE_APP = "APP";
	/**
	 * 统一下单URL
	 */
	public static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 统一下单 
	 * 除被扫支付场景以外，商户系统先调用该接口在 微信支付服务后台生成预支付交易单，返回正确
	 * 的预支付交易回话标识后再按扫码、JSAPI、APP 等不同场景生成交易串调起支付。
	 * 
	 * @param device_info
	 * @param body
	 * @param detail
	 * @param attach
	 * @param out_trade_no
	 * @param total_fee
	 * @param spbill_create_ip
	 * @param time_start
	 * @param time_expire
	 * @param goods_tag
	 * @param trade_type
	 * @param product_id
	 * @param limit_pay
	 */
	public static Map unifiedOrder(String device_info, String body, String detail, String attach, String out_trade_no,
			String total_fee, String spbill_create_ip, String time_start, String time_expire, String goods_tag,
			String trade_type, String product_id, String limit_pay) {
		// 构建请求xml
		String xml = getUnifiedOrderXML(device_info, body, detail, attach, out_trade_no, total_fee, spbill_create_ip,
				time_start, time_expire, goods_tag, trade_type, product_id, limit_pay);
		
		System.out.println(readStringXml(xml));
		String response = HttpsPost.post(UNIFIED_ORDER, xml);
		
		return readStringXml(response);

	}

	/**
	 * xml转map
	 * @param xml
	 * @return
	 */
	public static Map readStringXml(String xml) {
		Map map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();

			for (Element e : elements) {
				map.put(e.getName(), e.getText());
			}
			return map;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 构建统一下单xml
	 * 
	 * @param device_info
	 * @param body
	 * @param detail
	 * @param attach
	 * @param out_trade_no
	 * @param total_fee
	 * @param spbill_create_ip
	 * @param time_start
	 * @param time_expire
	 * @param goods_tag
	 * @param trade_type
	 * @param product_id
	 * @param limit_pay
	 * @return
	 */
	private static String getUnifiedOrderXML(String device_info, String body, String detail, String attach,
			String out_trade_no, String total_fee, String spbill_create_ip, String time_start, String time_expire,
			String goods_tag, String trade_type, String product_id, String limit_pay) {
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		document.setRootElement(root);
		root.addElement("appid").setText(APP_ID);
		root.addElement("mch_id").setText(MCH_ID);

		if (null != device_info && !"".equals(device_info)) {
			root.addElement("device_info").setText(device_info);
		}
		root.addElement("nonce_str").setText(getRandomString(32));
		root.addElement("body").setText(body);
		if (null != detail && !"".equals(detail)) {
			root.addElement("detail").setText(detail);
		}
		if (null != attach && !"".equals(attach)) {
			root.addElement("attach").setText(attach);
		}

		root.addElement("out_trade_no").setText(out_trade_no);
		// root.addElement("fee_type").setText("CNY");
		root.addElement("total_fee").setText(total_fee);
		root.addElement("spbill_create_ip").setText(spbill_create_ip);
		if (null != time_start && !"".equals(time_start)) {
			root.addElement("time_start").setText(time_start);
		}
		if (null != time_expire && !"".equals(time_expire)) {
			root.addElement("time_expire").setText(time_expire);
		}
		if (null != product_id && "".equals(goods_tag)) {
			root.addElement("goods_tag").setText(goods_tag);
		}

		root.addElement("notify_url").setText(NOTIFY_URL);
		root.addElement("trade_type").setText(trade_type);

		if (null != product_id && !product_id.equals("")) {
			root.addElement("product_id").setText(product_id);
		}
		if (null != limit_pay && !"".equals(limit_pay)) {
			root.addElement("limit_pay").setText(limit_pay);
		}
		if (null != OPEN_ID && !"".equals(OPEN_ID)) {
			root.addElement("openid").setText(OPEN_ID);
		}

		root.addElement("sign_type").setText(SIGN_TYPE_MD5);
		root.addElement("sign").setText(getSignStr(document));

		return root.asXML();
	}

	/**
	 * 获得字符串签名
	 * 
	 * @param document
	 * @return
	 */
	private static String getSignStr(Document document) {
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		List<String> names = new ArrayList<String>();
		Map<String, String> texts = new HashMap<String, String>();
		for (Element e : elements) {
			names.add(e.getName());
			texts.put(e.getName(), e.getText());
		}

		Collections.sort(names);

		StringBuffer sb = new StringBuffer();
		for (String n : names) {
			String key = n;
			String text = texts.get(key);
			if (text == null || text.equals("")) {
				continue;
			}
			sb.append(key).append("=").append(text).append("&");
			// System.out.println(key + "," + text);
		}

		sb.append("key").append("=").append(SIGN_KEY);

		// System.out.println(sb.toString());
		String sign = getMD5Str(sb.toString()).toUpperCase();
		// System.out.println(sign);
		return sign;
	}

	/**
	 * 获得MD5字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {
		try {
			// 创建加密对象
			MessageDigest digest = MessageDigest.getInstance("md5");

			// 调用加密对象的方法，加密的动作已经完成
			byte[] bs = digest.digest(str.getBytes());
			// 接下来，我们要对加密后的结果，进行优化，按照mysql的优化思路走
			// mysql的优化思路：
			// 第一步，将数据全部转换成正数：
			String hexString = "";
			for (byte b : bs) {
				// 第一步，将数据全部转换成正数：
				// 解释：为什么采用b&255
				/*
				 * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
				 * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
				 * 运算时： b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
				 * 0000 0000 0000 0000 0000 1111 1111 结果：0000 0000 0000 0000
				 * 0000 0000 1001 1100 此时的temp是一个int类型的整数
				 */
				int temp = b & 255;
				// 第二步，将所有的数据转换成16进制的形式
				// 注意：转换的时候注意if正数>=0&&<16，那么如果使用Integer.toHexString()，可能会造成缺少位数
				// 因此，需要对temp进行判断
				if (temp < 16 && temp >= 0) {
					// 手动补上一个“0”
					hexString = hexString + "0" + Integer.toHexString(temp);
				} else {
					hexString = hexString + Integer.toHexString(temp);
				}
			}
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static int getRandom(int count) {
		return (int) Math.round(Math.random() * (count));
	}

	private static String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 获得随机字符串
	 * 
	 * @param length
	 * @return
	 */
	private static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		for (int i = 0; i < length; i++) {
			sb.append(string.charAt(getRandom(len - 1)));
		}
		return sb.toString();
	}
	
	/**
	 * 获得客户端IP
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest  request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

	public static void main(String[] args) {
		String device_info = "";
		String body = "支付测试";
		String detail = "";
		String attach = "";
		String out_trade_no = "1415659990";
		String total_fee = "1";
		String spbill_create_ip = "14.23.150.211";
		String time_start = "20091225091010";
		String time_expire = "20091227091010";
		String goods_tag = null;
		String trade_type = "NATIVE";
		String product_id = "1";
		String limit_pay = "";

		Map map = unifiedOrder(device_info, body, detail, attach, out_trade_no, total_fee, spbill_create_ip, time_start,
				time_expire, goods_tag, trade_type, product_id, limit_pay);
		System.out.println(map);
	}

}
