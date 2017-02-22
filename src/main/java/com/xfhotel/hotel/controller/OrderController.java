package com.xfhotel.hotel.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.StringSplitUtil;
import com.xfhotel.hotel.support.TimeUtil;

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
	HttpSession session;

	@RequestMapping(value = "/module", method = RequestMethod.GET)
	public String orderModule(String startTime, String endTime, Integer totalDay, String price,
			String totalPrice, String preferential) {
		session.setAttribute("oStart", startTime);
		session.setAttribute("oEnd", endTime);
		session.setAttribute("oTotalDay", totalDay);
		session.setAttribute("oPrice", price);
		session.setAttribute("oTotalPrice", totalPrice);
		session.setAttribute("oPreferential", preferential);
		return "customer/orderModule";
	}
	
	
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.GET)
	public @ResponseBody Message checkAvailable(Long roomId,String startTime,String endTime){
		try {
			List<Order> availableOders = orderservice.checkAvailable(roomId, startTime, endTime);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, availableOders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查询失败");
		}
		
	}
	
	@RequestMapping(value = "/modulePost", method = RequestMethod.POST)
	public String orderModulePost(Long cusId, String description, Long roomId, String cusName, String cusTel,
			String cusIdCard, String personal, String startTime, String endTime, Integer totalDay, String price,
			String totalPrice, String preferential, boolean needFapiao, String apartmentType) {
		Order o = new Order();
		o.setCusId(cusId);
		o.setDescription(description);
		o.setRoomId(roomId);
		o.setCusName(cusName);
		o.setCusTel(cusTel);
		o.setCusIdCard(cusIdCard);
		o.setPersonal(personal);
		System.out.println(startTime);
		try {
			o.setStartTime(DateUtil.parse(startTime, "yyyy-MM-dd").getTime());
			o.setEndTime(DateUtil.parse(endTime, "yyyy-MM-dd").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.setTime(new Date().getTime());
		o.setTotalDay(totalDay);
		o.setPrice(price);
		o.setTotalPrice(totalPrice);
		o.setPreferential(preferential);
		o.setType(Apartment.getTypeNum(apartmentType));
		o.setStatus(Order.STATUS_ON_PAY);
		o.setNeedFapiao(needFapiao);
		orderservice.add(o);
		return "redirect:pay/"+o.getId();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody Message search(Long cId, int category, int type, String startDate, String endDate, int range) {
		try {
			List<Order> orders = orderservice.getCustomerOrders(cId, type);
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

	@RequestMapping(value = "/comment/post", method = RequestMethod.POST)
	public @ResponseBody Message postComment(Long roomId, Long from, Long to, String[] c_score, String feel,
			String[] pics) {
		try {
			Comment comment = new Comment();
			comment.setFromWho(from);
			comment.setToWho(to);
			comment.setRoomId(roomId);
			comment.setScore(StringSplitUtil.buildStrGroup(c_score));
			comment.setFeel(feel);
			comment.setPics(StringSplitUtil.buildStrGroup(pics));
			comment.setTime(new Date().getTime());

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
		System.out.println(order.toMap());
		return "customer/order";
	}

	@RequestMapping(value = "/payOver/{id}", method = RequestMethod.GET)
	public String payOver(@PathVariable("id") Long id, int status) {
		Order order = orderservice.get(id);
		if (status == Order.STATUS_TIME_OUT) {
			order.setStatus(status);
			session.setAttribute("err", "支付超时");
			return "/customer/err";
		}
		order.setStatus(status);
		orderservice.update(order);

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
}
