package com.xfhotel.hotel.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.FacilityOrder;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.SystemConfig;
import com.xfhotel.hotel.entity.TripOrder;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.FacilityOrderService;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.SystemConfService;
import com.xfhotel.hotel.service.TripOrderService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.sms.SendTemplateSMS;
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
	UserService userService;
	@Autowired
	TripOrderService tripOrderService;
	@Autowired
	LockService lockService;
	@Autowired
	OrderService orderService;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	CustomerService customerService;
	@Autowired
	FacilityOrderService facilityOrderService;
	@Autowired
	SystemConfService systemConfService;
	@Autowired
	CouponService couponService;

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
		if (o.getStatus() == Order.STATUS_ON_LEASE || o.getStatus() == Order.STATUS_ON_COMFIRM) {
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "已支付");
		}else{
				
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
	public String wechatOpenId(String code, String state,Integer id) {
		
		if (StringUtils.isBlank(code)) {// 用户拒绝授权
			// 跳转某URL
		} else {// 用户授权通过
			try {
				String authUrl = Config.AUTH_OPENID_URL.replace("CODE", code);
				JSONObject result = JSONObject.fromObject(HttpUtils.get(authUrl));
				System.out.println("微信授权============>\n"+result);
				if (result.containsKey("errcode")) {// 错误返回
					System.out.println(result);
				} else {
					String openId = result.getString("openid");
					Customer c = customerService.getCustomer(id);
					c.setWechatOpenId(openId);
					customerService.updateBaseInfo(c);
					return "redirect:../" + state;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:../login.html";
	}
	
	@RequestMapping("/auth/automatic")
	public String wechatAutomatic(String code, String state) {
		
		if (StringUtils.isBlank(code)) {// 用户拒绝授权
			// 跳转某URL
		} else {// 用户授权通过
			try {
				System.out.println(code);
				String authUrl = Config.AUTH_OPENID_URL.replace("CODE", code);
				JSONObject result = JSONObject.fromObject(HttpUtils.get(authUrl));
				System.out.println("微信授权============>\n"+result);
				if (result.containsKey("errcode")) {// 错误返回
					System.out.println(result);
				} else {
					String openId = result.getString("openid");
					String access_token = result.getString("access_token");	
					String authUrl1 = Config.AUTH_GANIN_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
					JSONObject basic = JSONObject.fromObject(HttpUtils.get(authUrl1));
					Customer c = customerService.getOpenId(openId);
					System.out.println(basic);
					if(c != null){
						if(c.getPassword()!=null){
							return "redirect:../" + state+"?id="+c.getId()+"&&"+"status=1";
						} else {
							return "redirect:../" + state+"?id="+c.getId()+"&&"+"status=0";
						}
					} else {
						Customer customer = new Customer();
						String Nick = basic.getString("nickname");
						String avatar = basic.getString("headimgurl");
						CustomerDetails details = new CustomerDetails(Nick, avatar);
						customer.setWechatOpenId(openId);
						customer.setConsumptionTimes(0);
						customer.setDetails(details);
						customer.setConsumptionCount(0.00F);
						customer.setRegTime(new Date().getTime());
						customer.setLevel(0);
						customer.setConsumptionCount(100F);
						customerService.register(customer, details);
						Customer c1 = customerService.getCustomer(customer.getId());
						Calendar calendar = Calendar.getInstance();
				        Date date = new Date(System.currentTimeMillis());
				        calendar.setTime(date);
				        calendar.add(Calendar.MONTH, +6);
				        date = calendar.getTime();
						List<Double> list = new ArrayList<Double>();
						list.add(20.0);
						list.add(30.0);
						list.add(50.0);
						List<String> list1 = new ArrayList<String>();
						list1.add("200");
						list1.add("300");
						list1.add("500");
						int d = 0;
						for(double cValue : list){
							for(int i =0;list1.size()>i;i++){
								String rule = list1.get(d);
								Coupon coupon = new Coupon();
								coupon.setcValue(cValue);
								coupon.setStartTime(new Date().getTime());
								coupon.setEndTime(date.getTime());
								coupon.setType(1);
								coupon.setRule(rule);
								coupon.setuId(c1.getId());
								couponService.add(coupon);
								d++;
								break;
							}
						}
						return "redirect:../" + state+"?id="+customer.getId()+"&&"+"status=0"+"&&"+"discounts=0";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:../login.html";
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
		System.out.println(ip);
		Order order = orderService.get(id);
		if (order == null){
			JSONObject jo = new JSONObject();
			jo.put("status", "error");
			jo.put("msg", "订单不存在");
			jo.put("obj", null);
			return jo;
		}
		order.setPayPlatform(Order.PAY_PLATFORM_WECHAT_JSAPI);
		orderService.update(order);
		String detail = order.getDescription();
		String desc = "青舍都市";
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
		JSONObject response = null;
		if("".equals(order.getWxQRCode()) || null == order.getWxQRCode()){
			// TODO 向微信下订单,并获取扫一扫地址
			String detail = order.getDescription();
			String desc = "青舍都市";
			String goodSn = "" + order.getRoomId();
			String orderSn = order.getPayNo();
			String amount = order.getTotalPrice();
			String type = "NATIVE";
			response = WechatOrderUtils.createOrder(detail, desc, "", ip, goodSn, orderSn, amount, type);
			order.setPayPlatform(Order.PAY_PLATFORM_WECHAT_NATIVE);
			if("success".equals(response.getString("status"))){
				order.setWxQRCode(((JSONObject)response.get("obj")).getString("url"));
			}
			orderService.update(order);
			return response;
		}else{
			response = new JSONObject();
			response.put("status", "success");
			JSONObject obj = new JSONObject();
			obj.put("url", order.getWxQRCode());
			response.put("obj", obj);
			return response;
		}
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
				FacilityOrder facilityOrder = facilityOrderService.getByPayNo(out_trade_no);
				TripOrder tripOrder = tripOrderService.getByPayNo(out_trade_no);
				if(o!=null){
					Customer customer = customerService.getCustomer(o.getCusId());
					SystemConfig system = systemConfService.getConfig();
					Float sum = (float) (customer.getConsumptionCount()+Float.parseFloat(o.getTotalPrice())-system.getYa_jin());
					customer.setConsumptionCount(sum);
					customerService.updateBaseInfo(customer);
					//更改订单状态
					o.setStatus(Order.STATUS_ON_COMFIRM);
					orderService.update(o);
					String pwd_user_mobile = o.getCusTel();
					JSONObject a = apartmentService.getApartmentById(o.getRoomId());
					String f= a.getJSONObject("position").getString("xiao_qu")+a.getString("lou_hao")+"号楼"+
							a.getString("dan_yuan")+"单元"+a.getString("lou_ceng")+"层"+a.getString("men_pai")+"号";
					String[] p = {f};
					User user = userService.findById(a.getLong("steward"));
					//发短信给顾客
					//【青舍都市】您预订的{1}已支付成功，管理员正在确认中，请耐心等待。
					SendTemplateSMS.sendSMS(Constants.SMS_INFORM_OVER_PAY, pwd_user_mobile, p);
					//发短信给管理员
					//【青舍都市】您有新订单需要确认，请及时处理。{1}
					if(user==null){
						SendTemplateSMS.sendSMS(Constants.SMS_INFORM_COMFIRM_ORDER, systemConfService.getConfig().getSms(), p);
					}else{
						SendTemplateSMS.sendSMS(Constants.SMS_INFORM_COMFIRM_ORDER, user.getContact(), p);
					}
				} else if(facilityOrder!=null){
					Customer customer = customerService.getCustomer(orderService.get(facilityOrder.getOederId()).getCusId());
					SystemConfig system = systemConfService.getConfig();
					Float sum = (float) (customer.getConsumptionCount()+Float.parseFloat(String.valueOf(facilityOrder.getPrice())));
					customer.setConsumptionCount(sum);
					customerService.updateBaseInfo(customer);
					//更改订单状态
					facilityOrder.setStatus(FacilityOrder.STATUS_NOT_AFFIRM);
					facilityOrderService.update(facilityOrder);
					String pwd_user_mobile = orderService.get(facilityOrder.getOederId()).getCusTel();
					JSONObject a = apartmentService.getApartmentById(o.getRoomId());
					String f= a.getJSONObject("position").getString("xiao_qu")+a.getString("lou_hao")+"号楼"+
							a.getString("dan_yuan")+"单元"+a.getString("lou_ceng")+"层"+a.getString("men_pai")+"号";
					String[] p = {f};
					User user = userService.findById(a.getLong("steward"));
					//发短信给顾客
					//【青舍都市】您预订的{1}已支付成功，管理员正在确认中，请耐心等待。
					SendTemplateSMS.sendSMS(Constants.SMS_INFORM_OVER_PAY, pwd_user_mobile, p);
					//发短信给管理员systemConfService
					//【青舍都市】您有新订单需要确认，请及时处理。{1}
					if(user==null){
						SendTemplateSMS.sendSMS(Constants.SMS_INFORM_ADD_FACILITY, systemConfService.getConfig().getSms(), p);
					}else{
						SendTemplateSMS.sendSMS(Constants.SMS_INFORM_ADD_FACILITY, user.getContact(), p);
					}
				} else if(tripOrder!=null){
					Customer customer = customerService.getCustomer(tripOrder.getCusId());
					SystemConfig system = systemConfService.getConfig();
					Float sum = (float) (customer.getConsumptionCount()+Float.parseFloat(String.valueOf(tripOrder.getPrice())));
					customer.setConsumptionCount(sum);
					customerService.updateBaseInfo(customer);
					//更改订单状态
					tripOrder.setStatus(TripOrder.STATUS_ON_COMFIRM);
					tripOrderService.update(tripOrder);
					String pwd_user_mobile = String.valueOf(tripOrder.getTel());
					JSONObject a = apartmentService.getApartmentById(orderService.get(tripOrder.getOederId()).getRoomId());
					String f= a.getJSONObject("position").getString("xiao_qu")+a.getString("lou_hao")+"号楼"+
							a.getString("dan_yuan")+"单元"+a.getString("lou_ceng")+"层"+a.getString("men_pai")+"号房间呼叫用车";
					String[] p = {f};
					//发短信给顾客
					//【青舍都市】您预订的{1}已支付成功，管理员正在确认中，请耐心等待。
					SendTemplateSMS.sendSMS(Constants.SMS_INFORM_OVER_PAY, pwd_user_mobile, p);
					//发短信给管理员systemConfService
					//【青舍都市】您有新订单需要确认，请及时处理。{1}
					User user = userService.findById(a.getLong("steward"));
					if(user==null){
						SendTemplateSMS.sendSMS(Constants.SMS_INFORM_ADD_FACILITY, systemConfService.getConfig().getSms(), p);
					}else{
						SendTemplateSMS.sendSMS(Constants.SMS_INFORM_ADD_FACILITY, user.getContact(), p);
					}	
				}
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
	
	@RequestMapping(value="/pay/jsAdd",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject jsAdd(Long[] id1, String ip) throws Exception {
		Long d = null;
		double g= 0.0; 
		for(Long id : id1){
			d =id;
			FacilityOrder facilityOrder = facilityOrderService.findById(id);
			facilityOrder.setPayPlatform(FacilityOrder.PAY_PLATFORM_WECHAT_JSAPI);
			facilityOrderService.update(facilityOrder);
			g+=facilityOrder.getPrice();
		}
		FacilityOrder facilityOrder = facilityOrderService.findById(d);
		if (facilityOrder == null){
			JSONObject jo = new JSONObject();
			jo.put("status", "error");
			jo.put("msg", "订单不存在");
			jo.put("obj", null);
			return jo;
		}
		String detail = facilityOrder.getRoomId();
		String desc = "青舍都市";
		Customer c = customerService.getCustomer(orderService.get(facilityOrder.getOederId()).getCusId());
		String openId = c.getWechatOpenId();
		String goodSn = "" + orderService.get(facilityOrder.getOederId()).getRoomId();
		String orderSn = facilityOrder.getPayNo();
		String amount = String.valueOf(g);
		String type = "JSAPI";
		JSONObject result = WechatOrderUtils.createOrder(detail, desc, openId, ip, goodSn, orderSn, amount, type);
		System.out.println("支付成功");
		return result;
	}
	
	
	@RequestMapping(value="/pay/jsTrip",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject jsTrip(Long id, String ip) throws Exception {
		TripOrder tripOrder = tripOrderService.findById(id);
		if (tripOrder == null){
			JSONObject jo = new JSONObject();
			jo.put("status", "error");
			jo.put("msg", "订单不存在");
			jo.put("obj", null);
			return jo;
		}
		tripOrder.setPayPlatform(TripOrder.PAY_PLATFORM_WECHAT_JSAPI);
		tripOrderService.update(tripOrder);
		String detail = tripOrder.getRoomName();
		String desc = "青舍都市";
		Customer c = customerService.getCustomer(tripOrder.getCusId());
		String openId = c.getWechatOpenId();
		String goodSn = "" + orderService.get(tripOrder.getOederId()).getRoomId();
		String orderSn = tripOrder.getPayNo();
		String amount = String.valueOf(tripOrder.getPrice());
		String type = "JSAPI";
		JSONObject result = WechatOrderUtils.createOrder(detail, desc, openId, ip, goodSn, orderSn, amount, type);
		System.out.println("支付成功");
		return result;
	}
}