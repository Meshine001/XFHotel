package com.xfhotel.hotel.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
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
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.SystemConfService;
import com.xfhotel.hotel.support.Area;
import com.xfhotel.hotel.support.LayoutType;
import com.xfhotel.hotel.support.LeasePrice;
import com.xfhotel.hotel.support.LeaseType;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.RoomStatus;
import com.xfhotel.hotel.support.SearchForm;
import com.xfhotel.hotel.support.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	ApartmentService apartmentService;

	@Autowired
	BlogService blogService;

	@Autowired
	SystemConfService systemConfService;

	@Autowired
	CommentService commentService;

	@Autowired
	HttpSession session;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		session.setAttribute("homeRoom", apartmentService.getHomeApartments());
		session.setAttribute("homeBlog", blogService.list(1, 10).getResults());
		return "/customer/home";
	}

	@RequestMapping(value = "/story", method = RequestMethod.GET)
	public String storyPage(HttpServletRequest request, int page) {

		PageResults<Blog> pr = blogService.show_blog(page);
		int sp = pr.getCurrentPage();
		int ep = pr.getPageCount();
		if ((sp - Constants.pagesize / 2) > 0) {
			sp = sp - Constants.pagesize / 2;
		} else {
			sp = 1;
		}
		if ((sp + Constants.pagesize - 1) < ep) {
			ep = sp + Constants.pagesize - 1;
		}
		if ((ep - Constants.pagesize + 1) > 0) {
			sp = ep - Constants.pagesize + 1;
		}
		request.getSession().setAttribute("blogs", pr);
		request.getSession().setAttribute("sp", sp);
		request.getSession().setAttribute("ep", ep);
		return "/customer/story";
	}

	@RequestMapping(value = "/serviceCenter", method = RequestMethod.GET)
	public String serviceCenterPage() {

		return "/customer/serviceCenter";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(SearchForm searchData, Integer currentPage) {
		if (null == currentPage) {
			currentPage = 1;
		}
		System.out.println(searchData);
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("searchData", searchData);
		info.put("areas", Area.getAreas());
		info.put("priceRanges", LeasePrice.getPrices());
		info.put("layoutTypes", LayoutType.getLayouts());
		info.put("enterTimes", RoomStatus.getStatusArray());
		info.put("leaseTypes", LeaseType.getLeaseTypes());
		info.put("page", apartmentService.getApartmentPage(
				apartmentService.sort(apartmentService.list(), searchData),
				currentPage));
		session.setAttribute("info", info);
		
		return "/customer/list";
	}

	@RequestMapping(value = "homeSearch", method = RequestMethod.GET)
	public String homeSearch(String checkinday, String checkoutday, String city) {
		// TODO
		StringBuffer sb = new StringBuffer("redirect:list?");
		SearchForm searchData = new SearchForm();
		searchData.setStartTime(checkinday);
		searchData.setEndTime(checkoutday);
		searchData.setArea(0);
		searchData.setPriceRange(0);
		searchData.setLayout(0);
		Long[] f = { 0L };
		searchData.setFeatures(f);
		searchData.setEnterTime(0);
		searchData.setLeaseType(Apartment.TYPE_ALL);
		searchData.setMoreStr(" ");
		searchData.setSortType(0);
		sb.append(searchData.toHttpGetPram());
		sb.append("&currentPage=1");
		return sb.toString();
	}


	/**
	 * 价格日历数据源
	 * 
	 * @param id
	 * @param startDate
	 * @return
	 */
	@RequestMapping(value = "/price/{id}/{startDate}", method = RequestMethod.GET)
	public @ResponseBody JSONObject getRangePrices(@PathVariable("id") Long id,
			@PathVariable("startDate") String startDate) {
		JSONArray prices = apartmentService.get2MonthPrices(id, startDate ,2);
		JSONObject data = new JSONObject();
		for (Object obj : prices) {
			String key = (String) JSONObject.fromObject(obj).keys().next();
			data.put(key, JSONObject.fromObject(obj).get(key));
		}

		return data;
	}

	@RequestMapping(value = "info/{apartmentId}", method = RequestMethod.GET)
	public String info(@PathVariable("apartmentId") Long id) {
		JSONObject apartment = apartmentService.getApartmentById(id);

		session.setAttribute("apartment", apartment);

		String start = TimeUtil.getDateStr(new Date().getTime());
		session.setAttribute("startDate", start);
		// 获得一周内的价钱
		session.setAttribute("prices", apartmentService.get7DaysPrices(id, start));
		// 获得评论
		session.setAttribute("roomRates", commentService.getRoomRates(id));

		session.setAttribute("yajin", systemConfService.getConfig().getYa_jin());

		session.setAttribute("allApartment", apartmentService.list());

		return "/customer/info";
	}

	@RequestMapping(value = "story/blog_content", method = RequestMethod.GET)
	public String initBlog(HttpServletRequest request, Long id) {
		request.setAttribute("id", id);

		return "/customer/story_content";
	}

	@RequestMapping(value = "story/load_content", method = RequestMethod.POST)
	public @ResponseBody Map loadBlog(HttpServletRequest request, Long id) {
		String path = request.getSession().getServletContext().getRealPath("/");
		Blog blog = blogService.find(id);
		path += "blog/" + blog.getPath();
		Map map = blog.toMap();
		StringBuffer content = new StringBuffer();
		FileReader fr;
		try {
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while ((str = br.readLine()) != null) {
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
