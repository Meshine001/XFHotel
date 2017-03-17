package com.xfhotel.hotel.support.pay;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

/**
 * 用于微信支付
 * 传输方式	为保证交易安全性，采用HTTPS传输
 * 提交方式	采用POST方法提交
 * 数据格式	提交和返回数据都为XML格式，根节点名为xml
 * 字符编码	统一采用UTF-8字符编码
 * 签名算法	MD5，后续会兼容SHA1、SHA256、HMAC等。
 * 签名要求	请求和接收数据均需要校验签名，详细方法请参考安全规范-签名算法
 * 证书要求	调用申请退款、撤销订单接口需要商户证书
 * 判断逻辑	先判断协议字段返回，再判断业务返回，最后判断交易状态
 * @author Ming
 *
 */
public class WechatPaySDK {
	
	/**
	 * 统一下单URL
	 */
	public static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	public static void unifiedOrder(){
		 Document document = DocumentHelper.createDocument();
		 Element root = DocumentHelper.createElement("xml");
		 document.setRootElement(root);
		 root.addElement("appid").setText("wx2421b1c4370ec43b");
		 root.addElement("attach").setText("支付测试");
		 root.addElement("body").setText("JSAPI支付测试");
		 root.addElement("mch_id").setText("10000100");
		 
		 
		 XMLWriter xmlWriter = new XMLWriter();
	        try {
				xmlWriter.write(document);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		unifiedOrder();
	}
	
}
