package com.xfhotel.hotel.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.StringSplitUtil;
import com.xfhotel.hotel.support.TimeUtil;
import com.xfhotel.hotel.support.pay.WechatPaySDK;
import com.xfhotel.hotel.support.wechat.WechatOrderUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderservice;
	@Autowired
	RoomService roomService;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	CommentService commentService;
	@Autowired
	LockService lockService;
	
	@Autowired
	HttpSession session;

	/**
	 * 退租
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/outLease", method = RequestMethod.POST)
	@ResponseBody
	public Message outLease(Long orderId) {
		try {
			Order o = orderservice.get(orderId);
			// TODO 退押金,
			String[] prices = o.getPrice().split("@");
			String refundFee = prices[prices.length - 1];
			// 若是微信支付的
			if (Order.PAY_PLATFORM_WECHAT_JSAPI.equals(o.getPayPlatform())
					|| Order.PAY_PLATFORM_WECHAT_NATIVE.equals(o.getPayPlatform())) {
				JSONObject result = WechatOrderUtils.refund(o.getPayNo(), o.getPayNo(), o.getTotalPrice(), refundFee);
				if ("success".equals(result.getString("status"))) {
					o.setStatus(Order.STATUS_COMPLETE);
					orderservice.update(o);
					return new Message(Constants.MESSAGE_SUCCESS_CODE, "退租成功");
				} else {
					return new Message(Constants.MESSAGE_ERR_CODE, "退租失败");
				}
			} else {
				o.setStatus(Order.STATUS_COMPLETE);
				orderservice.update(o);
				return new Message(Constants.MESSAGE_SUCCESS_CODE, "退租成功");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "退租失败");
		}
	}

	/**
	 * 返回订单模板，在房间信息页，点击‘立即预定’触发
	 * 
	 * @param startTime
	 * @param endTime
	 * @param totalDay
	 * @param price
	 * @param totalPrice
	 * @param preferential
	 * @return
	 */
	@RequestMapping(value = "/module", method = RequestMethod.GET)
	public String orderModule(String startTime, String endTime, Long apartmentId) throws Exception {
		session.setAttribute("oStart", startTime);
		session.setAttribute("oEnd", endTime);
		System.out.println(startTime + endTime);
		Map<String, Object> priceInfo = apartmentService.caculatePrice(startTime, endTime, apartmentId);
		System.out.println(priceInfo);
		session.setAttribute("oTotalDay", TimeUtil.daysBetween(startTime, endTime));
		session.setAttribute("oPrice", priceInfo.get("price"));
		session.setAttribute("oTotalPrice", priceInfo.get("totalPrice"));
		session.setAttribute("oCashPledge", priceInfo.get("cashPledge"));
		session.setAttribute("oPreferential", "");
		session.setAttribute("capacity", priceInfo.get("capacity"));
		return "customer/orderModule";
	}

	@RequestMapping(value = "/checkAvailable", method = RequestMethod.GET)
	public @ResponseBody Message checkAvailable(Long roomId, String startTime, String endTime) {
		try {
			List<Order> availableOders = orderservice.checkAvailable(roomId, startTime, endTime);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, availableOders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查询失败");
		}

	}

	/**
	 * 用户提交订单 房间详细信息页，确认订单触发
	 * 
	 * @param cusId
	 * @param description
	 * @param roomId
	 * @param cusName
	 * @param cusTel
	 * @param cusIdCard
	 * @param personal
	 * @param startTime
	 * @param endTime
	 * @param totalDay
	 * @param price
	 * @param totalPrice
	 * @param preferential
	 * @param needFapiao
	 * @param apartmentType
	 * @return
	 */
	@RequestMapping(value = "/modulePost", method = RequestMethod.POST)
	public String orderModulePost(Long cusId, String description, Long roomId, String cusName, String cusTel,
			 String otherCusName,String otherCusIdCard,String cusIdCard, String personal, String startTime, String endTime, Integer totalDay, String price,
			String totalPrice, String preferential, boolean needFapiao, String apartmentType) {
		Order o = new Order();
		o.setCusId(cusId);
		o.setDescription(description);
		o.setRoomId(roomId);
		o.setCusName(cusName);
		o.setCusTel(cusTel);
		o.setCusIdCard(cusIdCard);
		o.setPersonal(personal);
		o.setOtherCusName(otherCusName);
		o.setOtherCusIdCard(otherCusIdCard);
		try {
			o.setStartTime(DateUtil.parse(startTime + " 12:00", "yyyy-MM-dd HH:mm").getTime());
			o.setEndTime(DateUtil.parse(endTime + " 12:00", "yyyy-MM-dd HH:mm").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.setTime(new Date().getTime());
		o.setTotalDay(totalDay);
		Map room = roomService.getRoomInfo(roomId);
		Apartment apartment = apartmentService.findById((Long)room.get("apartment"));
		o.setPrice(price+"@"+apartment.getYajin());
		o.setTotalPrice(totalPrice);
		o.setPreferential(preferential);
		o.setType(Apartment.getTypeNum(apartmentType));
		o.setStatus(Order.STATUS_ON_PAY);
		o.setNeedFapiao(needFapiao);
		orderservice.add(o);

		return "redirect:pay/" + o.getId();
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String orderDetails(Long orderId) {
		// TODO
		Order o = orderservice.get(orderId);
		session.setAttribute("order", o);
		return "customer/orderDetails";
	}
	
	/**
	 * 管理员确认订单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/comfirm", method = RequestMethod.POST)
	@ResponseBody
	public Message comfirmOrder(Long id){
		Order o = orderservice.get(id);
		if(o == null){
			return new Message(Constants.MESSAGE_ERR_CODE, "无此订单");
		}
		if(o.getStatus() == Order.STATUS_ON_COMFIRM){
			try {
				//发送门锁密码
				Long roomId = o.getRoomId();
				Long apartment = (Long) roomService.getRoomInfo(roomId).get("apartment");
				String lock_no = (String) apartmentService.getApartmentInfo(apartment).get("lock_address");
				lockService.addPassword(o.getCusTel(), lock_no, DateUtil.format(new Date(o.getStartTime()), "yyyyMMddhhmmss"),
						DateUtil.format(new Date(o.getEndTime()), "yyyyMMddhhmmss"));
				o.setStatus(Order.STATUS_ON_LEASE);
				orderservice.update(o);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Message(Constants.MESSAGE_ERR_CODE, "订单确认失败");
			}
			
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "订单确认成功");
		
	}

	/**
	 * 用户查看房间密码
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/viewLockPsd", method = RequestMethod.GET)
	public String viewLockPsd(Long orderId) {
		// TODO 还需要加入一些权限限制
		Order o = orderservice.get(orderId);

		session.setAttribute("lockPsd", "还没弄好");
		return "customer/viewLockPsd";
	}

	/**
	 * 查询订单
	 * 
	 * @param cId
	 * @param category
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody Message search(Long cId, int category, int type, String startDate, String endDate, int range) {
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

	/**
	 * 跳转到订单评价页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
	public String comment(@PathVariable("id") Long id) {
		Map<String, Object> order = orderservice.get(id).toMap();
		Map<String, Object> room = roomService.getRoomInfo((Long) order.get("roomId"));
		session.setAttribute("order", order);
		session.setAttribute("room", room);
		Map<String, Object> apartment = apartmentService.getApartmentInfo((Long) room.get("apartment"));
		session.setAttribute("apartment", apartment);
		return "customer/comment";
	}

	/**
	 * 提交评论
	 * 
	 * @param roomId
	 * @param from
	 * @param to
	 * @param c_score
	 * @param feel
	 * @param pics
	 * @return
	 */
	@RequestMapping(value = "/comment/post", method = RequestMethod.POST)
	public @ResponseBody Message postComment(Long roomId, Long orderId, Long from, Long to, String[] c_score,
			String feel, String[] pics) {
		try {
			Comment comment = new Comment();
			comment.setFromWho(from);
			comment.setToWho(to);
			comment.setRoomId(roomId);
			comment.setScore(StringSplitUtil.buildStrGroup(c_score));
			comment.setFeel(feel);
			comment.setPics(StringSplitUtil.buildStrGroup(pics));
			comment.setTime(new Date().getTime());
			comment.setHasRead(false);

			Order o = orderservice.get(orderId);
			comment.setEntryTime(o.getStartTime());

			commentService.add(comment);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "评论成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "评论失败");
		}
	}

	@RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
	public String pay(@PathVariable("id") Long id) {
		Order order = orderservice.get(id);
		session.setAttribute("order", order.toMap());
		return "customer/order";
	}

	/**
	 * 交易完成
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/payOver/{id}", method = RequestMethod.GET)
	public String payOver(@PathVariable("id") Long id) {
		Order order = orderservice.get(id);
		if (order.getStatus() == Order.STATUS_TIME_OUT) {
			session.setAttribute("err", "支付超时");
			return "/customer/err";
		}

		Room room = roomService.findById(order.getRoomId());
		room.setStatus(Room.STATUS_LEASED);
		roomService.update(room);

		return "/customer/success";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Long cusId, Long roomId, Long apartmentId, String cusName, String cusTel, String cusPersonal,
			String startTime, String endTime, int type, String cusIdCard, boolean needFapiao) {
		Order order = null;
		try {

			Room room = roomService.findById(roomId);
			if (room.getStatus() == Room.STATUS_LEASED || room.getStatus() == Room.STATUS_ON_PAY) {
				session.setAttribute("err", "房间已租出");
				return "/customer/err";
			}

			Map roomInfo = roomService.getRoomInfo(roomId);
			Map apartmentInfo = apartmentService.getApartmentInfo(apartmentId);

			StringBuffer sb = new StringBuffer();
			sb.append(apartmentInfo.get("community") + " ");
			sb.append(apartmentInfo.get("floor") + "/" + apartmentInfo.get("totalfloor") + " ");

			order = new Order();
			order.setCusId(cusId);
			order.setDescription(sb.toString());
			order.setRoomId(roomId);
			order.setCusName(cusName);
			order.setCusTel(cusTel);
			order.setCusIdCard(cusIdCard);
			order.setPersonal(cusPersonal);
			String patterns = "yy-MM-dd";
			Date start = DateUtils.parseDate(startTime, patterns);
			Date end = DateUtils.parseDate(endTime, patterns);
			order.setStartTime(start.getTime());
			order.setEndTime(end.getTime());

			long diff = end.getTime() - start.getTime();
			int days = (int) (diff / (1000 * 60 * 60 * 24));

			order.setTotalDay(days);
			String[] prices = (String[]) roomInfo.get("prices");
			order.setPrice(prices[0]);
			if (type == Apartment.TYPE_HOTEL) {
				Double p = Double.valueOf(prices[0]);
				Double totalPrice = p * days;
				order.setTotalPrice(String.valueOf(totalPrice));
			}
			order.setType(type);
			order.setStatus(Order.STATUS_ON_PAY);
			order.setTime(new Date().getTime());
			order.setNeedFapiao(needFapiao);
			orderservice.add(order);

			room.setStatus(Room.STATUS_ON_PAY);
			roomService.update(room);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			session.setAttribute("err", "无法生成订单");
			return "/customer/err";
		}

		return "redirect:/order/pay/" + order.getId();
	}

	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public String msg(int msg) {
		StringBuffer sb = new StringBuffer();

		switch (msg) {

		case Order.STATUS_TIME_OUT:
			sb.append("订单超时");
			break;
		case Order.STATUS_COMPLETE:
			sb.append("订单已完成");
			break;
		case Order.STATUS_CANCEL:
			sb.append("订单已取消");
			break;
		default:
			break;
		}
		session.setAttribute("orderMsg", sb.toString());
		return "customer/orderMessage";
	}
}
