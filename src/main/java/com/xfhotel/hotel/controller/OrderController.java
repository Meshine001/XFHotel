package com.xfhotel.hotel.controller;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderservice;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Long cusId, String description, Long roomId, String cusName, String cusTel, String personal,
			String startTime, String endTime, int totalDay, String price, int type,String cusIdCard) {
		try {
			Order order = new Order();
			order.setCusId(cusId);
			order.setDescription(description);
			order.setRoomId(roomId);
			order.setCusName(cusName);
			order.setCusTel(cusTel);
			order.setCusIdCard(cusIdCard);
			order.setPersonal(personal);
			String patterns = "yy-MM-dd";
			Date start = DateUtils.parseDate(startTime, patterns);
			Date end = DateUtils.parseDate(endTime, patterns);
			order.setStartTime(start.getTime());
			order.setEndTime(end.getTime());

			order.setTotalDay(totalDay);
			order.setPrice(price);
			if(type == Order.TYPE_HOTEL){
				Double p = Double.valueOf(price);
				Double totalPrice = p*totalDay;
				order.setTotalPice(String.valueOf(totalPrice));
			}
			
			order.setType(type);
			order.setStatus(Order.STATUS_NOT_COMPLETE);
			
			order.setTime(new Date().getTime());
			
			orderservice.add(order);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "customer/order";
	}
}
