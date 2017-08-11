package com.xfhotel.hotel.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Site;
import com.xfhotel.hotel.entity.TripOrder;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.SiteService;
import com.xfhotel.hotel.service.TripOrderService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("triporder")
public class TripOrderController {
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	TripOrderService tripOrderService;
	
	@Autowired
	OrderService orderservice;

	@RequestMapping(value = "/tripOrderAdd", method = RequestMethod.POST)
	public @ResponseBody Message tripOrderAdd ( Long OrderId, Long tripId ,Long startTime ,Long endTime ,double price,Long tel ,String demand ,String site1 ) {
		TripOrder tripOrder = new TripOrder();
		try {
			Site site = siteService.findById(tripId);
			String classify = null;
			Order order = orderservice.get(OrderId);
			tripOrder.setRoomName(order.getDescription());
			tripOrder.setEndTime(endTime);
			tripOrder.setStartTime(startTime);
			tripOrder.setTel(tel);
			tripOrder.setPrice(price);
			tripOrder.setDemand(demand);
			tripOrder.setCusId(order.getCusId());
			tripOrder.setTime(new Date().getTime());
			tripOrder.setSite(site1);
			tripOrder.setTripId(site.getPlace());
			int classify1 = site.getClassify();
			if(classify1 ==0){
				classify="接送";
			}else{
				classify="包车";
			}
			tripOrder.setClassify(classify);
			tripOrder.setStatus(TripOrder.STATUS_ON_PAY);
			tripOrderService.add(tripOrder);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, tripOrder );
	}
	
	@RequestMapping(value = "/tipOrders", method = RequestMethod.POST)
	@ResponseBody
	public Message tipOrders(Long id) {
		TripOrder c = tripOrderService.findById(id);
		if (c == null) {
			return new Message(Constants.MESSAGE_ERR_CODE, "无此订单");
		}
		if (c.getStatus() == TripOrder.STATUS_ON_COMFIRM) {
			try {
					c.setStatus(TripOrder.STATUS_ON_LEASE);
					tripOrderService.update(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Message(Constants.MESSAGE_ERR_CODE, "订单确认失败");
			}
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加完成");
		}
	
	@RequestMapping(value = "/tipOrder", method = RequestMethod.POST)
	@ResponseBody
	public Message tipOrder(Long id) {
		TripOrder c = tripOrderService.findById(id);
		if (c == null) {
			return new Message(Constants.MESSAGE_ERR_CODE, "无此订单");
		}
		if (c.getStatus() == TripOrder.STATUS_COMPLETE) {
			try {
					c.setStatus(TripOrder.STATUS_COMPLETE);
					tripOrderService.update(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Message(Constants.MESSAGE_ERR_CODE, "订单确认失败");
			}
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加完成");
		}
}
