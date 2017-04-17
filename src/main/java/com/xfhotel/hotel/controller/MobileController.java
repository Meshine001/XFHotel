package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BannerService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("mobile")
public class MobileController {
	@Autowired
	RoomService roomService;
	
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	BannerService bannerService;
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/home",method = RequestMethod.GET)
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
	public @ResponseBody Map login(String tel, String password) {
		Customer c = customerService.login(tel, password);
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("c", c);
		return info;
	}
	


	
}
