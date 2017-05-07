package com.xfhotel.hotel.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Banner;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BannerService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.Area;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.LayoutType;
import com.xfhotel.hotel.support.LeasePrice;
import com.xfhotel.hotel.support.LeaseType;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.RoomStatus;
import com.xfhotel.hotel.support.SearchForm;
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
	BlogService blogService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CommentService commentService;
	
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
		session.setAttribute("homeBlog", blogService.list(1,10).getResults()); 
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
	public String storyPage(HttpServletRequest request, int page){

		PageResults<Blog> pr = blogService.show_blog(page);
		int sp = pr.getCurrentPage();
		int ep = pr.getPageCount();
		if ( (sp-Constants.pagesize/2) > 0){
			sp = sp-Constants.pagesize/2;
		}
		else{
			sp=1;
		}
		if( (sp+Constants.pagesize-1) < ep ){
			ep = sp+Constants.pagesize-1;
		}
		if( (ep-Constants.pagesize+1) > 0 ){
			sp = ep-Constants.pagesize+1;
		}
		request.getSession().setAttribute("blogs",pr);
		request.getSession().setAttribute("sp",sp);
		request.getSession().setAttribute("ep",ep);
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
	public String list(SearchForm searchData,Integer currentPage) {
		if(null == currentPage){
			currentPage = 1;
		}
		System.out.println(searchData);
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("searchData", searchData);
		info.put("areas", Area.getAreas());
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
		
		info.put("page",  getApartmentPage(sort(apartmentService.list(),searchData), currentPage));
		session.setAttribute("info", info);
		return "/customer/list";
	}
	
	/**
	 * 构造分页信息
	 * @param list
	 * @param currentPage
	 * @return
	 */
	private PageResults<Map> getApartmentPage(List<Map> list,int currentPage){
		PageResults<Map> page = new PageResults<Map>();
		int totalCount = list.size();
		int pageSize = 5;
		int t = totalCount/pageSize;
		int pageCount = t*pageSize < totalCount ?t+1:t;
		int pageNo = currentPage<pageCount?currentPage+1:currentPage;
		page.setTotalCount(totalCount);
		page.setPageSize(pageSize);
		page.setPageCount(pageCount);
		page.setPageNo(pageNo);
		page.setCurrentPage(currentPage);
		int m = (currentPage-1)*pageSize;
		int n = m+pageSize;
		List<Map> results = new ArrayList<Map>();
		for(int i = m;i<totalCount && i<n;i++){
			results.add(list.get(i));
		}
		page.setResults(results);
		return page;
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
		sb.append("&currentPage=1");
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

	/**
	 * 获取两个月内的价格
	 * @param id
	 * @param startDate
	 * @return
	 */
	public List<Map> get2MonthPrices(Long id,String startDate){
		List<Map> prices = new ArrayList<Map>();
		Apartment apartment = apartmentService.findById(id);
		Map aInfo = apartmentService.getApartmentInfo(id);
		Map room = ((ArrayList<Map>)aInfo.get("rooms")).get(0);
		Long start = TimeUtil.getDateLong(startDate+" 12:00","yyyy-MM-dd hh:mm");
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
		
		List<Order> availableOrders = orderService.checkAvailable(
				(Long)room.get("id"), 
				TimeUtil.getDateStr(start), 
				TimeUtil.getDateStr(end));
		
		for(Date d:allDates){
			Map<String, Object> info = new HashMap<String, Object>();
			Map<String, Object> details = new HashMap<String, Object>();
			details.put("price", apartment.getPrices());
			details.put("start", DateUtil.format(d, "yyyy-MM-dd"));
			details.put("pricetype", "normal");
			details.put("roomNum", "1");
			
			
			for(Map m:sp){
				String t = (String) m.get("date");
				if(DateUtil.format(d, "yyyy-MM-dd").equals(t)){
					details.put("price",((Double)m.get("price")));
				}
			}
			for(Order o:availableOrders){
				Long tt = d.getTime()+1000*60*60*12;
				if( tt>o.getStartTime() && tt<o.getEndTime()){
					details.put("roomNum", "0");
				}
			}
			info.put(DateUtil.format(d, "yyyy-MM-dd"), details);
			prices.add(info);
		}
		return prices;
	}
	
	/**
	 * 价格日历数据源
	 * @param id
	 * @param startDate
	 * @return
	 */
	@RequestMapping(value = "/price/{id}/{startDate}", method = RequestMethod.GET)
	public @ResponseBody Map getRangePrices(@PathVariable("id")Long id,@PathVariable("startDate")String startDate){
		List<Map> prices = get2MonthPrices(id, startDate);
		Iterator<Map> iterator = prices.iterator();
		Map data = new HashMap<String, Object>();
		while(iterator.hasNext()){
			Map m = iterator.next();
			String date = (String) m.keySet().iterator().next();
			data.put(date, m.get(date));
		}
		
		return data;
	}
	
	@RequestMapping(value="/price/script")
	public String calendarScript(){
		return "customer/priceCalendarScript";
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
		System.out.println(apartment);
		String start = TimeUtil.getDateStr(new Date().getTime());
		session.setAttribute("startDate", start);
		//获得两个月的价格
		List<Map> priceMaps = get2MonthPrices((Long)room.get("apartment"), start);
		List<Map> prices = new ArrayList<Map>();
		int s = Integer.parseInt(start.substring(start.length()-2, start.length()))-1;
		Iterator<Map> iterator = priceMaps.iterator();
		int i = 0;
		while(iterator.hasNext()&&i<(s+6)){
			if(i<s){
				iterator.next();
				i++;
				continue;
			}
			Map<String, Object> info = iterator.next();
			String date = info.keySet().iterator().next();
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("date", date.substring(date.length()-2, date.length()));
			Map<String, Object> details = (Map<String, Object>) info.get(date);
			m.put("price", details.get("price"));
			prices.add(m);
			i++;
		}

		session.setAttribute("prices", prices);
		
		session.setAttribute("roomRates", commentService.getRoomRates(roomId));
		session.setAttribute("yajin",apartment.get("yajin"));
		
		List<Map> allApartment = apartmentService.list();
		session.setAttribute("allApartment", allApartment);
		
		return "/customer/info";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {

		return "/customer/reservation1";
	}
	

	@RequestMapping(value = "story/blog_content", method = RequestMethod.GET)
	public String initBlog(HttpServletRequest request,Long id){
		request.setAttribute("id", id);
		
		return "/customer/story_content";
	}
	
	@RequestMapping(value = "story/load_content", method = RequestMethod.POST)
	public @ResponseBody Map loadBlog(HttpServletRequest request,Long id) {
		String path = request.getSession().getServletContext().getRealPath("/");
		Blog blog = blogService.find(id);
		path += "blog/" + blog.getPath();
		Map map = blog.toMap();
		StringBuffer content = new StringBuffer();
		FileReader fr;
		try {
			fr = new FileReader(path);
			BufferedReader br=new BufferedReader(fr);
			String str;
			while( ( str=br.readLine())!=null){
				content.append(str);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("content", content.toString());
		
		return map;
	}
}
