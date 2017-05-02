package com.xfhotel.hotel.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swetake.util.Qrcode;
import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.QRCode;
import com.xfhotel.hotel.support.TimeUtil;
import com.xfhotel.hotel.support.wechat.Config;
import com.xfhotel.hotel.support.wechat.HttpUtils;
import com.xfhotel.hotel.support.wechat.Log;
import com.xfhotel.hotel.support.wechat.SignatureUtils;
import com.xfhotel.hotel.support.wechat.WechatOrderUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx")
public class WechatController {
	@Autowired
	HttpSession session;

	@Autowired
	LockService lockService;
	@Autowired
	OrderService orderService;
	@Autowired
	RoomService roomService;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	CustomerService customerService;

	/**
	 * 查询订单是否已经支付
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/isPayed")
	@ResponseBody
	public Message isPayed(Long id) {
		Order o = orderService.get(id);
		if (o.getStatus() == Order.STATUS_ON_LEASE) {
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "已支付");
		}else{
//			JSONObject result = WechatOrderUtils.query(o.getPayNo());
//			try {
//				if("success".equals(result.getString("status")) && null!=result.getString("trade_state")){
//					return new Message(Constants.MESSAGE_SUCCESS_CODE, "已支付");
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			}
		}
		
		return new Message(Constants.MESSAGE_ERR_CODE, "未支付");
	}

	/**
	 * 查询订单
	 * @param id 业务系统订单id
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public JSONObject query(Long id){
		Order o = orderService.get(id);
		JSONObject result = WechatOrderUtils.query(o.getPayNo());
		return result;
	}
	
	/**
	 * 退款
	 * @param id
	 * @param refundFee
	 * @return
	 */
	@RequestMapping(value = "/refund",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject refund(Long id,String refundFee){
		Order o = orderService.get(id);
		JSONObject result = WechatOrderUtils.refund(o.getPayNo(), o.getPayNo(), o.getTotalPrice(), refundFee);
		return result;
	}
	
	/**
	 * 获取二维码
	 * 
	 * @param url
	 * @param response
	 */
	@RequestMapping(value = "QRCode", method = RequestMethod.GET)
	public void QRCode(String url, HttpServletResponse response) throws Exception {
		// 调用框架生成二维码
		Qrcode handler = new Qrcode();
		handler.setQrcodeErrorCorrect('M');
		handler.setQrcodeEncodeMode('B');
		handler.setQrcodeVersion(7);

		byte[] contentBytes = url.getBytes("UTF-8");

		BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);

		Graphics2D gs = bufImg.createGraphics();

		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, 140, 140);

		// 设定图像颜色：BLACK
		gs.setColor(Color.BLACK);

		// 设置偏移量 不设置肯能导致解析出错
		int pixoff = 2;
		// 输出内容：二维码
		if (contentBytes.length > 0 && contentBytes.length < 124) {
			boolean[][] codeOut = handler.calQrcode(contentBytes);
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		} else {
			Log.error("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ", null);
		}

		gs.dispose();
		bufImg.flush();

		// 生成二维码QRCode图片
		ImageIO.write(bufImg, "jpg", response.getOutputStream());
	}

	/**
	 * 用户授权后重定向的URL
	 * 
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping("/auth/openId")
	public String wechatOpenId(String code, String state) {
		System.out.println("auth:============>"+code+","+state);
		if (StringUtils.isBlank(code)) {// 用户拒绝授权
			// 跳转某URL
		} else {// 用户授权通过
			try {
				String authUrl = Config.AUTH_OPENID_URL.replace("CODE", code);
				JSONObject result = JSONObject.fromObject(HttpUtils.get(authUrl));
				System.out.println(result);
				if (result.containsKey("errcode")) {// 错误返回
					System.out.println(result);
				} else {
					session.setAttribute("wechatAuth", result);
					return "redirect:" + state;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:index.html";
	}

	/**
	 * 公共号支付
	 * @param id
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pay/jsOrder",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject jsOrder(Long id, String ip) throws Exception {
		Order order = orderService.get(id);
		if (order == null){
			JSONObject jo = new JSONObject();
			jo.put("status", "error");
			jo.put("msg", "订单不存在");
			jo.put("obj", null);
			return jo;
		}
		order.setPayPlatform(Order.PAY_PLATFORM_WECHAT);
		orderService.update(order);

		String detail = order.getDescription();
		String desc = "房间预定";
		Customer c = customerService.getCustomer(order.getCusId());
		String openId = c.getWechatOpenId();
		String goodSn = "" + order.getRoomId();
		String orderSn = order.getPayNo();
		String amount = order.getTotalPrice();
		String type = "JSAPI";
		
		JSONObject result = WechatOrderUtils.createOrder(detail, desc, openId, ip, goodSn, orderSn, amount, type);
		return result;
	}

	/**
	 * 二维码支付
	 * @param id
	 * @param ip
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/pay/nativeOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject nativeOrder(Long id, String ip) throws IOException {
		Order order = orderService.get(id);
		if (order == null)
			return null;

		// TODO 向微信下订单,并获取扫一扫地址
		String detail = order.getDescription();
		String desc = "青舍都市";
		String goodSn = "" + order.getRoomId();
		// String orderSn = ""+order.getId();
		String orderSn = order.getPayNo();
		String amount = order.getTotalPrice();
		String type = "NATIVE";
		JSONObject response = WechatOrderUtils.createOrder(detail, desc, "", ip, goodSn, orderSn, amount, type);
		order.setPayPlatform(Order.PAY_PLATFORM_WECHAT);
		orderService.update(order);
		
		return response;
	}

	/**
	 * APP调起微信支付接口
	 * 
	 * @param detail
	 *            商品描述
	 * @param desc
	 *            商品详情
	 * @param goodSn
	 *            商品编号
	 * @param openId
	 *            用户openid
	 * @param orderSn
	 *            订单号
	 * @param amount
	 *            金额
	 * @return 返回包装了调起微信APPSDK所需要的函数
	 * @throws Exception
	 */
	@RequestMapping("/pay/appOrder")
	@ResponseBody
	public String appOrder(String detail, String desc, String goodSn, String openId, String orderSn, String amount)
			throws Exception {
		JSONObject result = WechatOrderUtils.createOrder(detail, desc, "", "10.0.0.1", goodSn, orderSn, amount, "APP");
		return result.toString();
	}

	/**
	 * 微信支付回调函数
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/pay/callback", method = RequestMethod.POST)
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		HashMap<String, String> map = new HashMap<String, String>();
		Log.info("------------微信回调函数----------------", null);
		// 1、读取传入信息并转换为map
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		String payType = "";
		String memberId = "";
		Element root = document.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			if (e.getName().trim().equals("payType")) {
				payType = e.getText().trim();
			} else if (e.getName().trim().equals("memberId")) {
				memberId = e.getText().trim();
			} else {
				map.put(e.getName().trim(), e.getText().trim());
			}
		}
		is.close();
		// System.out.println(map.toString());
		// 2、克隆传入的信息并进行验签
		HashMap<String, String> signMap = (HashMap<String, String>) map.clone();
		signMap.remove("sign");
		Log.info(map.toString(), null);
		String key = Config.WX_KEY;
		String sign = SignatureUtils.signature(signMap, key);
		// System.out.println(sign);
		// System.out.println(map.get("sign"));
		if (!sign.equals(map.get("sign"))) {
			Log.error("微信支付回调函数：验签错误", null);
			return;
		}
		// 信息处理
		String result_code = map.get("result_code");
		try {

			if ("SUCCESS".equals(result_code)) {
				// 由于微信后台会同时回调多次，所以需要做防止重复提交操作的判断
				// 此处放防止重复提交操作
				String out_trade_no = map.get("out_trade_no");
				Order o = orderService.getByPayNo(out_trade_no);
				//发送门锁密码
				Long roomId = o.getRoomId();
				Long apartment = (Long) roomService.getRoomInfo(roomId).get("apartment");
				String lock_no = (String) apartmentService.getApartmentInfo(apartment).get("lock_address");
				lockService.addPassword(o.getCusTel(), lock_no, TimeUtil.getDateStr(o.getStartTime()),
						TimeUtil.getDateStr(o.getEndTime()));
				//更改订单状态
				o.setStatus(Order.STATUS_ON_LEASE);
				orderService.update(o);
					
			} else if ("FAIL".equals(result_code)) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			// 进行业务逻辑操作

		} catch (Exception e) {
			e.printStackTrace();
			Log.error("回调用户中心错误", e);
		}

		// 返回信息，防止微信重复发送报文
		String result = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
				+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
		PrintWriter out = new PrintWriter(response.getOutputStream());
		out.print(result);
		out.flush();
		out.close();
	}

}
