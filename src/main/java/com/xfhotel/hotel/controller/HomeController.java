package com.xfhotel.hotel.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.LayoutType;
import com.xfhotel.hotel.support.LeasePrice;
import com.xfhotel.hotel.support.LeaseType;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.RoomStatus;
import com.xfhotel.hotel.support.SearchForm;
import com.xfhotel.hotel.support.Subway;
import com.xfhotel.hotel.support.TimeUtil;

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

		info.put("list",  sort(apartmentService.list(),searchData));
		System.out.println(searchData);
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
	
	public List sort(List list, SearchForm searchData) {
		double rates[] = new double[list.size()];
		for(int i=0; i<list.size(); i++){ 
			Object obj = list.get(i);
			rates[i] = searchData.rate((Map) obj);
		}
		
		for(int i=0; i<list.size(); i++){
			for(int j=i+1; j<list.size(); j++){
				int check=0;
				if( rates[i] == rates[j] ){
					switch(searchData.getSortType()){
					case 0:{
						Map obj1 = (Map) list.get(i);
						Map obj2 = (Map) list.get(j);
						//compare
						break;
					}
					case 1:{
						Map obj1 = (Map) list.get(i);
						Map obj2 = (Map) list.get(j);
						//compare
						double price1 = Double.valueOf(((String[])obj1.get("prices"))[0]);
						double price2 = Double.valueOf(((String[])obj2.get("prices"))[0]);
						if( price1>price2 )
							check=1;
						break;
					}
					case 2:{
						Map obj1 = (Map) list.get(i);
						Map obj2 = (Map) list.get(j);
						//compare
						double square1 = (Double)obj1.get("square");
						double square2 = (Double)obj2.get("square");
						if( square1<square2 )
							check=1;
						break;
					}
					}
				}
				if( rates[i] < rates[j] || rates[i] == rates[j] && check==1){
					Object tmp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, tmp);
					double t = rates[i];
					rates[i] = rates[j];
					rates[j] = t;
				}
			}
		}
		return list;
	}

	public List<Map> get2MonthPrices(Long id,String startDate){
		List<Map> prices = new ArrayList<Map>();
		Apartment apartment = apartmentService.findById(id);
		Long start = TimeUtil.getDateLong(startDate);
		Long end = TimeUtil.getDatePlusMonth(new Date(start), 2).getTime();
		//未来两个月特殊价格
		List<Map> sp= apartmentService.getSpPrices(start, end, apartment);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(start));
		int curM = calendar.get(Calendar.MONTH);
		int curY = calendar.get(Calendar.YEAR);
		List<Date> allDates = TimeUtil.getAllDateInMonth(curY,curM);
		if(curM == 12){
			allDates.addAll(TimeUtil.getAllDateInMonth(curY+1,0));
		}else{
			allDates.addAll(TimeUtil.getAllDateInMonth(curY,curM+1));
		}
		
		for(Date d:allDates){
			Map<String, Object> info = new HashMap<String, Object>();
			info.put("houseprice", apartment.getPrices());
			info.put("start", DateUtil.format(d, "yyyy-MM-dd"));
			info.put("pricetype", "normal");
			info.put("state", "available");
			for(Map m:sp){
				String t = (String) m.get("date");
				if(DateUtil.format(d, "yyyy-MM-dd").equals(t)){
					info.put("houseprice",((Double)m.get("price")));
				}
			}
			prices.add(info);
		}
		return prices;
	}
	
	@RequestMapping(value = "/price/{id}/{startDate}", method = RequestMethod.GET)
	public @ResponseBody List<Map> getRangePrices(@PathVariable("id")Long id,@PathVariable("startDate")String startDate){
		return get2MonthPrices(id, startDate);
	}
	
	/**
	 * 计算价钱
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public @ResponseBody Map calcuPrice(Long id,String start,String end){
		Map<String, Object> p = new HashMap<String, Object>();
		List<Map> prices = get2MonthPrices(id, start);
		Double sum = 0D;
		boolean f = false;
		int day = 0;
		List<Double> sb = new ArrayList<Double>();
		for(Map m:prices){
			String d = (String) m.get("start");
			if(d.equals(start))f=true;
			if(f){
				String prStr = String.valueOf(m.get("houseprice"));
				Double pr = Double.valueOf(prStr);
				sum += pr;
				day++;
				sb.add(pr);
				if(d.equals(end)){
					day -= 1;
					sum -= pr;
					sb.remove(sb.size()-1);
					break;
				}
			}
		}
		StringBuffer sbb = new StringBuffer();
		for(int i=0;i<sb.size();i++){
			sbb.append(sb.get(i));
			if(i!=(sb.size()-1)){
				sbb.append("@");
			}
		}
		
		p.put("start", start);
		p.put("end", end);
		p.put("totalPrice", sum);
		p.put("totalDay", day);
		p.put("price", sbb.toString());
		return p;
	}
	
	@RequestMapping(value = "info/{roomId}", method = RequestMethod.GET)
	public String info(@PathVariable("roomId") Long roomId) {
		Map room = roomService.getRoomInfo(roomId);
		Map apartment = apartmentService.getApartmentInfo((Long)room.get("apartment"));
		session.setAttribute("room", room);
		session.setAttribute("apartment", apartment);
		String start = TimeUtil.getDateStr(new Date().getTime());
		session.setAttribute("startDate", start);
		List<Map> priceMaps = get2MonthPrices((Long)room.get("apartment"), start);
		List<Map> prices = new ArrayList<Map>();
		int s = Integer.parseInt(start.substring(start.length()-2, start.length()))-1;
		for(int i= s;i<priceMaps.size();i++){
			if(i == s+6){
				break;
			}
			Map<String, Object> m = new HashMap<String, Object>();
			String dStr = (String) priceMaps.get(i).get("start");
			m.put("date", dStr.substring(dStr.length()-2, dStr.length()));
			m.put("price", priceMaps.get(i).get("houseprice"));
			prices.add(m);
		}
		session.setAttribute("prices", prices);
		return "/customer/info";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {

		return "/customer/reservation1";
	}
	
}
