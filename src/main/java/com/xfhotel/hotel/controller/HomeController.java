package com.xfhotel.hotel.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.Area;
import com.xfhotel.hotel.support.LayoutType;
import com.xfhotel.hotel.support.LeasePrice;
import com.xfhotel.hotel.support.LeaseType;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.RoomStatus;
import com.xfhotel.hotel.support.Subway;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	FeatureService featrueService;
	@Autowired
	RoomService roomService;
	@Autowired
	ApartmentService apartmentService;
	
	@Autowired
	HttpSession session;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "/customer/home";
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map() {

		return "/customer/map";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		session.setAttribute("areas", Area.getAreas());
		session.setAttribute("subways", Subway.getSubways());
		session.setAttribute("leasePrices", LeasePrice.getPrices());
		session.setAttribute("leaseTypes", LeaseType.getLeaseTypes());
		session.setAttribute("layoutTypes", LayoutType.getLayouts());
		List<Feature> fs = featrueService.listFeatures();
		List<com.xfhotel.hotel.support.Feature> features = new ArrayList<com.xfhotel.hotel.support.Feature>();
		for(Feature f:fs){
			features.add(new com.xfhotel.hotel.support.Feature((int)f.getId(), f.getDescription()));
		}
		com.xfhotel.hotel.support.Feature.setFeatures(features);
		session.setAttribute("features",com.xfhotel.hotel.support.Feature.getFeatures());
		session.setAttribute("roomStatus", RoomStatus.getStatusArray());
			
		return "/customer/list";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public @ResponseBody Message search() {
		try {
			List<Map>  rooms = roomService.getAllRooms();
			Apartment apartment = new Apartment();
			Room room = new Room();
			int  type=0;//0 缺省，1单租，2合租
			String content = "";
			apartmentService.findApartment(type, apartment);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, rooms);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "获取失败");
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody User jsonTest() {
		User u = new User();
		u.setId(1);
		u.setUsername("user");
		u.setPassword("123456");
		u.setAuthority(1);
		return u;
	}
	
	@RequestMapping(value = "info/{roomId}", method = RequestMethod.GET)
	public String info(@PathVariable("roomId") Long roomId) {
		Map room = roomService.getRoomInfo(roomId);
		
		session.setAttribute("room", room);
		
		return "/customer/info";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {

		return "/customer/reservation1";
	}
	
}
