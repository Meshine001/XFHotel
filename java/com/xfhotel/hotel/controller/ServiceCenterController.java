package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/ServiceCenter")
public class ServiceCenterController {
	@Autowired
	OrderService orderservice;

	@Autowired
	CleanService cleanservice;

	@RequestMapping(value = "/Clean", method = RequestMethod.GET)
	public @ResponseBody  ArrayList<Object> Clean(Long uId ,int type){
		List<Order> o = orderservice.getCustomerOrders(uId, type);
		Map<String, Object> map = new HashMap<String, Object>();
	for(Order d : o){
		if( d.getStatus()==2){
			map.put(d.getCusName(), d);
		}
	}
	ArrayList<Object> list = new ArrayList<Object>();
	  for(String key : map.keySet()){
	   list.add(map.get(key));
	  }
	  return list;
		
	}
	
	@RequestMapping(value = "/cleanAdd", method = RequestMethod.POST)
	public @ResponseBody Message cleanAdd (String demand,Long oederId , int content1[],int cleanTime) {
		System.out.println("那还好9");
		if(content1==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "请选择服务内容");
		}
		try {
			System.out.println("那还好0");
			Order o = orderservice.get(oederId);
			System.out.println("那还好1");
			Clean clean = new Clean();
			System.out.println("那还好2");
			clean.setDemand(demand);
			clean.setCleanTime(Clean.getcleanTime(cleanTime));
			clean.setContent(Clean.getTypeDescription(content1));
			clean.setRoomId(o.getRoomId());
			clean.setOederId(oederId);
			clean.setTime(new Date().getTime());
			clean.setStatus(Clean.STATUS_NOT_AFFIRM);
			System.out.println("那还好");
			cleanservice.add(clean);
			System.out.println("那还好6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		
		return new Message(Clean.STATUS_NOT_AFFIRM, "等待管理员确认");
	}
	@RequestMapping(value = "/cleanOrder", method = RequestMethod.POST)
	@ResponseBody
	public Message cleanOrder(Long id) {
		Clean c = cleanservice.get(id);
		if (c == null) {
			return new Message(Constants.MESSAGE_ERR_CODE, "无此订单");
		}
		if (c.getStatus() == Clean.STATUS_NOT_AFFIRM) {
			try {
					c.setStatus(Clean.STATUS_CONDUCT);
					cleanservice.update(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Message(Constants.MESSAGE_ERR_CODE, "订单确认失败");
			}

		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "订单确认成功");

	}
	
	@RequestMapping(value = "/cleanOrders", method = RequestMethod.POST)
	@ResponseBody
	public Message cleanOrders(Long id) {
		Clean c = cleanservice.get(id);
		if (c == null) {
			return new Message(Constants.MESSAGE_ERR_CODE, "无此订单");
		}
		if (c.getStatus() == Clean.STATUS_CONDUCT) {
			try {
					c.setStatus(Clean.STATUS_COMPLETE);
					cleanservice.update(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Message(Constants.MESSAGE_ERR_CODE, "订单确认失败");
			}

		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "打扫完成");

	}

}
