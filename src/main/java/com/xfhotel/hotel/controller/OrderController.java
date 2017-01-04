package com.xfhotel.hotel.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;

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
	HttpSession session;

	@RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
	public String pay(@PathVariable("id") Long id) {
		Order order = orderservice.get(id);
		session.setAttribute("order", order.toMap());
		return "customer/order";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Long cusId, Long roomId, Long apartmentId, String cusName, String cusTel, String cusPersonal,
			String startTime, String endTime, int type, String cusIdCard, boolean needFapiao) {
		Order order = null;
		try {
			
			Room room = roomService.findById(roomId);
			Map roomInfo = roomService.getRoomInfo(roomId);
			Map apartmentInfo = apartmentService.getApartmentInfo(apartmentId);

			StringBuffer sb = new StringBuffer();
			sb.append(apartmentInfo.get("community") + " ");
			sb.append(apartmentInfo.get("floor") + "/" + apartmentInfo.get("totalfloor") + " ");
			sb.append(roomInfo.get("description"));

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
			if (type == Order.TYPE_HOTEL) {
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
			e.printStackTrace();
		}

		return "redirect:/order/pay/" + order.getId();
	}
}
