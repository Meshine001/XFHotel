package com.xfhotel.hotel.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.xfhotel.hotel.entity.Banner;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BannerService;
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
	BannerService bannerService;
	
	@Autowired
	HttpSession session;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		List<Banner> homeBanner = bannerService.getHomeBanner();
		session.setAttribute("homeBanner", homeBanner);
		
		List<Room> rooms = roomService.getHomeRooms();
		List<Map> homeRooms = new ArrayList<Map>();
		for(Room room :rooms){
			Long apartmentId = (Long) roomService.getRoomInfo(room.getId()).get("apartment");
			homeRooms.add(apartmentService.getApartmentInfo(apartmentId));
		}
		System.out.println(homeRooms);
		session.setAttribute("homeRoom", homeRooms);
		return "/customer/home";
	}
	
	@RequestMapping(value = "/test/upload",method = RequestMethod.GET)
	public String uploadTest(){
		return "/test/upload";
	}
	
	@RequestMapping(value = "/test/calendar",method = RequestMethod.GET)
	public String calendarTest(){
//		return "/test/calendar";
		return "/test/priceCalendar";
	}
	
	@RequestMapping(value = "/test/prices",method = RequestMethod.GET)
	public @ResponseBody List calendarPricesTest(){
		List<Object> list = new ArrayList<Object>();
		for(int i=1;i<9;i++){
			Map<String, String> info = new HashMap<String, String>();
			String date = "2017-01-0"+i;
			String price = "100"+i;
			info.put("Date", date);
			info.put("Price", price);
			list.add(info);
		}
		return list;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(String type,String checkinday,String checkoutday,String city) {
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
		if(type.equals(Constants.TYPE_PLAY_ROOM)){
			session.setAttribute("searchType", Constants.TYPE_PLAY_ROOM);
		}else if(type.equals(Constants.TYPE_HOTEL)){
			session.setAttribute("searchType", Constants.TYPE_HOTEL);
		}else{
			session.setAttribute("searchType", Constants.TYPE_ALL);
		}
		return "/customer/list";
	}
	
	@RequestMapping(value = "homeSearch", method = RequestMethod.GET)
	public String homeSearch(String checkinday,String checkoutday,String city){
		//TODO
		
		return "redirect:list?checkinday="+checkinday+"&checkoutday="+checkoutday+"&city="+city+"&type="+Constants.TYPE_ALL;
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public @ResponseBody Message search() {
		try {
			List<Map>  rooms = roomService.getAllRooms();
			Apartment apartment = new Apartment();
			apartment.setLayout("2@2@1@1");
			apartment.setType("单租型");
			String fs = "";
			apartment.setFeatures(null);
			apartment.setPrice_scope("1");
			Room room = new Room();
			String content = "";
			String subways[] = new String[]{"1"};
			for(int i=0;i<subways.length;i++){
				fs = fs + "@" + subways[i];
			}
			apartmentService.findApartment(content, apartment);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, rooms);
		} catch (Exception e) {
			// TODO Auto-generated catch blockp/0;'+
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
		Map apartment = apartmentService.getApartmentInfo((Long)room.get("apartment"));
		session.setAttribute("room", room);
		session.setAttribute("apartment", apartment);
		return "/customer/info";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {

		return "/customer/reservation1";
	}
	
}
