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
import com.xfhotel.hotel.support.SearchForm;
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
	
	@RequestMapping(value = "/story",method = RequestMethod.GET)
	public String storyPage(){
		
		return "/customer/story";
	}
	
	@RequestMapping(value = "/serviceCenter",method = RequestMethod.GET)
	public String serviceCenterPage(){
		
		return "/customer/serviceCenter";
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
	public String list(SearchForm searchData) {
		System.out.println(searchData);
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("searchData", searchData);
		info.put("areas", Area.getAreas());
//		info.put("subways", Subway.getSubways());
		info.put("priceRanges", LeasePrice.getPrices());
		info.put("layoutTypes", LayoutType.getLayouts());
		List<Feature> fs = featrueService.listFeatures();
		List<com.xfhotel.hotel.support.Feature> features = new ArrayList<com.xfhotel.hotel.support.Feature>();
		for(Feature f:fs){
			features.add(new com.xfhotel.hotel.support.Feature((int)f.getId(), f.getDescription()));
		}
		com.xfhotel.hotel.support.Feature.setFeatures(features);
		info.put("features",com.xfhotel.hotel.support.Feature.getFeatures());
		info.put("enterTimes", RoomStatus.getStatusArray());
		info.put("leaseTypes", LeaseType.getLeaseTypes());
		
		//TODO

		info.put("list",  apartmentService.list());
		System.out.println(info.get("list"));
		session.setAttribute("info", info);
		return "/customer/list";
	}
	
	@RequestMapping(value = "homeSearch", method = RequestMethod.GET)
	public String homeSearch(String checkinday,String checkoutday,String city){
		//TODO
		StringBuffer sb = new StringBuffer("redirect:list?");
		SearchForm searchData = new SearchForm();
		searchData.setStartTime(checkinday);
		searchData.setEndTime(checkoutday);
		searchData.setArea(0);
		searchData.setPriceRange(0);
		searchData.setLayout(0);
		Long[] f = {0L};
		searchData.setFeatures(f);
		searchData.setEnterTime(0);
		searchData.setLeaseType(Apartment.TYPE_ALL);
		searchData.setMoreStr(" ");
		searchData.setSortType(0);
		sb.append(searchData.toHttpGetPram());
		return sb.toString();
	}
	public int cmp(Object obj1, Object obj2, SearchForm searchData) {
		return searchData.cmp((Map)obj1, (Map)obj2);
	}
	
	public List sort(List list, SearchForm searchData) {
		for(int i=0; i<list.size(); i++){
			for(int j=i+1; i<list.size(); j++){
				if(cmp(list.get(i),list.get(j),searchData)>0){
					Object tmp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, tmp);
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody List jsonTest() {
		
		List<Map> list = new ArrayList<Map>();
		for(int i=1;i<10;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("houseprice", 2103);
			map.put("state", "available");
			map.put("start", "2017-02-0"+i);
			map.put("pricetype", "normal");
			list.add(map);
		}
		for(int i=0;i<10;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("houseprice", 2103);
			map.put("state", "available");
			map.put("start", "2017-02-1"+i);
			map.put("pricetype", "normal");
			list.add(map);
		}
		for(int i=0;i<9;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("houseprice", 2103);
			map.put("state", "available");
			map.put("start", "2017-02-2"+i);
			map.put("pricetype", "normal");
			list.add(map);
		}
		return list;
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
