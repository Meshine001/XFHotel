package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BannerService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.TimeUtil;
import com.xfhotel.hotel.support.sms.SendTemplateSMS;



@Controller
@RequestMapping("mobile")
public class MobileController  {
	private static final int String = 0;

	private static final int HashMap = 0;

	@Autowired
	RoomService roomService;
	
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	BannerService bannerService;
	@Autowired
	CustomerService customerService;
	
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	HttpSession session;
	

	
	
	@RequestMapping(value = "/home",method = RequestMethod.POST)
	public @ResponseBody Map home(){
		List<Room> rooms = roomService.getHomeRooms();
		List<Map> homeRooms = new ArrayList<Map>();
		for(Room room :rooms){
			Long apartmentId = (Long) roomService.getRoomInfo(room.getId()).get("apartment");
			homeRooms.add(apartmentService.getApartmentInfo(apartmentId));
		}
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("homeRooms",homeRooms);
		return info;
		
	}
	@RequestMapping(value = "/info",method = RequestMethod.POST)
	public @ResponseBody Map info(Long roomId){
		Map room = roomService.getRoomInfo(roomId);
		Map apartment = apartmentService.getApartmentInfo((Long)room.get("apartment"));
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("room", room);
		info.put("apartment", apartment);
		return info;
		
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody  Message login(String tel, String password) {
		
		Customer c = customerService.login(tel, password);
		List<Customer>  list = customerService.list();
		System.out.println(list);
		if (c != null) {
			session.setAttribute("c", c);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "登录成功");
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, "账号或密码错误");
		}

	}
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public @ResponseBody Message reg(String tel, String password,Model model) {
		if (customerService.checkTel(tel)) {
			return new Message(Constants.MESSAGE_ERR_CODE, "手机号已使用");
		}
		CustomerDetails details = new CustomerDetails(tel, Constants.DEFAULT_AVATAR, tel);
		Customer c = new Customer(tel, password);
		c.setConsumptionTimes(0);
		c.setConsumptionCount(0.00F);
		c.setDetails(details);
		c.setRegTime(new Date().getTime());
		c.setLevel(0);

		if (customerService.register(c, details) == true) {
			session.setAttribute("c", c);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "注册成功");
		}

		return new Message(Constants.MESSAGE_ERR_CODE, "注册失败");
	}

	@RequestMapping(value="/checkVCode")  
	public @ResponseBody Message checkVCode(String tel,String vCode){
		try {
			Map<String, Object> sVCode = (Map<String, Object>) session.getAttribute("vCode");
			String sTel = (String) sVCode.get("tel");
			long diedLine = (Long) sVCode.get("diedLine");
			String code = (String) sVCode.get("code");

			if(sTel.equals(tel) && code.equals(vCode)){
				if(diedLine < new Date().getTime()){
					return new Message(Constants.MESSAGE_ERR_CODE, "验证超时");
					
				}
				return new Message(Constants.MESSAGE_SUCCESS_CODE, "验证成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("12365");
			return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
		}
		System.out.println("123");
		return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
	}
	
	/**
	 * 请求发送注册手机验证码
	 * @param tel
	 * @return
	 */
	@RequestMapping(value="/sendVCode")  
	public @ResponseBody Message sendTelValidateCode(String tel){
		try {
			String vCodeStr= SendTemplateSMS.generateValidateCode();
			String[] args = {vCodeStr,Constants.SMS_AVAILBEL_TIME_STR};
			//调试时可注释掉下面
			//发送短信
//			SendTemplateSMS.sendSMS(Constants.SMS_TEMPLATE_REG, tel, args);
			//利用session进行验证
			Map<String, Object> vCode = new HashMap<String, Object>();
			vCode.put("tel", tel);
			vCode.put("diedLine", new Date().getTime()+Constants.SMS_AVAILBEL_TIME);
			vCode.put("code", vCodeStr);
			session.setAttribute("vCode", vCode);
			System.out.println("send vcode==>"+vCode);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "获取验证码失败");
		}
		
	}
	@RequestMapping(value = "/module", method = RequestMethod.POST)
	public  @ResponseBody Map orderModule(String startTime, String endTime, Long apartmentId) throws Exception {
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("oStart", startTime);
		info.put("oEnd", endTime);
		Map<String, Object> priceInfo = caculatePrice(startTime, endTime, apartmentId);
		info.put("oTotalDay",TimeUtil.daysBetween(startTime, endTime));
		info.put("oPrice", priceInfo.get("price"));
		info.put("oTotalPrice", priceInfo.get("totalPrice"));
		info.put("oPreferential", "");
		return info;
	}
	
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
	public @ResponseBody Message checkAvailable(Long roomId, String startTime, String endTime) {
		System.out.println(startTime);
		System.out.println(roomId);
		System.out.println(endTime);
		try {
			List<Order> availableOders = orderservice.checkAvailable(roomId, startTime, endTime);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, availableOders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查询失败");
		}

	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody Message search(Long cId, int category, int type, String startDate, String endDate, int range)
	{
		try {
			List<Order> orders = orderservice.search(cId, category, type, startDate, endDate, range);
			List<Map> maps = new ArrayList<Map>();
			for (Order o : orders) {
				Map m = o.toMap();
				Map room = roomService.getRoomInfo(o.getRoomId());
				Map apartment = apartmentService.getApartmentInfo((Long) room.get("apartment"));
				m.put("apartment", apartment);
				maps.add(m);
				
			}
			return new Message(Constants.MESSAGE_SUCCESS_CODE, maps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "获取失败");
	}
	
	public Map<String, Object> caculatePrice(String startTime, String endTime, Long apartmentId) {
		Map<String, Object> info = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Double sum = 0.00D;
		Apartment apartment = apartmentService.findById(apartmentId);
		List<String> days = TimeUtil.getBetweenDays(startTime, endTime);
		for (int i = 0; i < days.size() - 1; i++) {
			Price p = apartmentService.getSpPrice(apartment, TimeUtil.getDateLong(days.get(i)));
			Double pp = null;
			if (p != null) {// 有特殊价格
				pp = p.getPrice();
			} else {
				pp = Double.valueOf(apartment.getPrices());
			}
			if (i == 0) {
				sb.append(pp);
			} else {
				sb.append("@" + pp);
			}
			sum += pp;
		}
		info.put("price", sb.toString());
		info.put("totalPrice", sum);
		return info;
	}

}
