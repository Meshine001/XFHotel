package com.xfhotel.hotel.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BannerService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.support.Area;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.LayoutType;
import com.xfhotel.hotel.support.LeasePrice;
import com.xfhotel.hotel.support.LeaseType;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.RoomStatus;
import com.xfhotel.hotel.support.SearchForm;
import com.xfhotel.hotel.support.StringSplitUtil;
import com.xfhotel.hotel.support.TimeUtil;
import com.xfhotel.hotel.support.sms.SendTemplateSMS;
import com.xfhotel.hotel.support.wechat.WechatOrderUtils;

import net.sf.json.JSONObject;



@Controller
@RequestMapping("mobile")
public class MobileController  {

	
	@Autowired
	FeatureService featrueService;

	@Autowired
	RoomService roomService;
	
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	BannerService bannerService;
	@Autowired
	CustomerService customerService;
	@Autowired
	CouponService couponService;
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	BlogService blogService;

	
	@RequestMapping(value = "/home",method = RequestMethod.POST)
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
	public @ResponseBody  Message login(String tel, String password) {
		Customer c = customerService.login(tel, password);
		if (c != null) {
			session.setAttribute("c", c);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, c);
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, "账号或密码错误");
		}

	}
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public @ResponseBody  Message find(String tel ,String psd) {
		
		if (customerService.checkTel(tel)) {
			Customer c = customerService.getFind(tel);
			String content = customerService.changePsd(c.getPassword(), psd, c.getId());
			if ("修改成功".equals(content)) {
				customerService.logout();
				return new Message(Constants.MESSAGE_SUCCESS_CODE, content);
			} else {
				return new Message(Constants.MESSAGE_ERR_CODE, content);
			}
			
		}
			return new Message(Constants.MESSAGE_ERR_CODE, "该手机号未注册");
		

	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public @ResponseBody Message reg(String tel, String password,Model model) {
		if (customerService.checkTel(tel)) {
			return new Message(Constants.MESSAGE_ERR_CODE, "手机号已使用");
		}
		CustomerDetails details = new CustomerDetails(tel, Constants.DEFAULT_AVATAR, tel);
		Customer c = new Customer(tel, password);
		c.setConsumptionTimes(0);
		c.setConsumptionCount(0.00F);
		c.setDetails(details);
		c.setRegTime(new Date().getTime());
		c.setLevel(0);

		if (customerService.register(c, details) == true) {
			session.setAttribute("c", c);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, c.getId());
		}

		return new Message(Constants.MESSAGE_ERR_CODE, "注册失败");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody PageResults<Comment> getRoomComments(Long roomId,Integer page){
		return commentService.getComments(roomId, page);
	}
	
	@RequestMapping(value = "/getRoomRates", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRoomRates(Long roomId){
		return commentService.getRoomRates(roomId);
	}

	
	
	@RequestMapping(value="/checkVCode")  
	public @ResponseBody Message checkVCode(String tel,String vCode){
		System.out.println(session.getId());
		try {
			Map<String, Object> sVCode = (Map<String, Object>) session.getAttribute("vCode");
			String sTel = (String) sVCode.get("tel");
			long diedLine = (Long) sVCode.get("diedLine");
			String code = (String) sVCode.get("code");

			if(sTel.equals(tel) && code.equals(vCode)){
				if(diedLine < new Date().getTime()){
					return new Message(Constants.MESSAGE_ERR_CODE, "验证超时");
					
				}
				return new Message(Constants.MESSAGE_SUCCESS_CODE, "验证成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("12365");
			return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
		}
		System.out.println("123");
		return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
	}
	
	/**
	 * 请求发送注册手机验证码
	 * @param tel
	 * @return
	 */
	@RequestMapping(value="/sendVCode")  
	public @ResponseBody Message sendTelValidateCode(String tel){
		System.out.println(session.getId());
		try {
			String vCodeStr= SendTemplateSMS.generateValidateCode();
			String[] args = {vCodeStr,Constants.SMS_AVAILBEL_TIME_STR};
			//调试时可注释掉下面
			//发送短信
			SendTemplateSMS.sendSMS(Constants.SMS_TEMPLATE_REG, tel, args);
			//利用session进行验证
			Map<String, Object> vCode = new HashMap<String, Object>();
			vCode.put("tel", tel);
			vCode.put("diedLine", new Date().getTime()+Constants.SMS_AVAILBEL_TIME);
			vCode.put("code", vCodeStr);
			session.setAttribute("vCode", vCode);
			//System.out.println("send vcode==>"+vCode);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "获取验证码失败");
		}
		
	}
	
	@RequestMapping(value = "/module", method = RequestMethod.POST)
	public  @ResponseBody Map orderModule(String startTime, String endTime, Long apartmentId) throws Exception {
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("oStart", startTime);
		info.put("oEnd", endTime);
		Map<String, Object> priceInfo = apartmentService.caculatePrice(startTime, endTime, apartmentId);
		info.put("oTotalDay",TimeUtil.daysBetween(startTime, endTime));
		info.put("oPrice", priceInfo.get("price"));
		info.put("oTotalPrice", priceInfo.get("totalPrice"));
		info.put("oCashPledge", priceInfo.get("cashPledge"));
		info.put("oPreferential", "");
		System.out.println();
		return info;
	}
	
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
	public @ResponseBody Message checkAvailable(Long roomId, String startTime, String endTime) {
		System.out.println(startTime);
		System.out.println(roomId);
		System.out.println(endTime);
		try {
			List<Order> availableOders = orderservice.checkAvailable(roomId, startTime, endTime);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, availableOders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查询失败");
		}

	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody Message search(Long cId, int category, int type, String startDate, String endDate, int range)
	{
		
		try {
			List<Order> orders = orderservice.search(cId, category, type, startDate, endDate, range);
			List<Map> maps = new ArrayList<Map>();
			for (Order o : orders) {
				Map m = o.toMap();
				Map room = roomService.getRoomInfo(o.getRoomId());
				Map apartment = apartmentService.getApartmentInfo((Long) room.get("apartment"));
				m.put("apartment", apartment);
				maps.add(m);
				 
			}
			return new Message(Constants.MESSAGE_SUCCESS_CODE, maps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "获取失败");
	}
	/*
	 * 获取用户资料
	 */
	@RequestMapping(value = "/detailsData", method = RequestMethod.POST)
	public @ResponseBody Customer getCustomerDetails(Long id){
		return customerService.getCustomer(id);
	}
	/**
	 * 评论
	 * 
	 * @param roomId
	 * @param orderId
	 * @param from
	 * @param to
	 * @param c_score
	 * @param feel
	 * @param pics
	 * @return
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public @ResponseBody Message postComment(Long roomId, Long orderId, Long from, Long to, String[] c_score,
			String feel, String[] pics) {
		try {
			Comment comment = new Comment();
			comment.setFromWho(from);
			comment.setToWho(to);
			comment.setRoomId(roomId);
			comment.setScore(StringSplitUtil.buildStrGroup(c_score));
			comment.setFeel(feel);
			comment.setPics(StringSplitUtil.buildStrGroup(pics));
			comment.setTime(new Date().getTime());
			comment.setHasRead(false);
			Order o = orderservice.get(orderId);
			comment.setEntryTime(o.getStartTime());

			commentService.add(comment);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "评论成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "评论失败");
		}
	}
	/**
	 * 用户提交订单 房间详细信息页，确认订单触发
	 * 
	 * @param cusId
	 * @param description
	 * @param roomId
	 * @param cusName
	 * @param cusTel
	 * @param cusIdCard
	 * @param personal
	 * @param startTime
	 * @param endTime
	 * @param totalDay
	 * @param price
	 * @param totalPrice
	 * @param preferential
	 * @param needFapiao
	 * @param apartmentType
	 * @return
	 */
	@RequestMapping(value = "/modulePost", method = RequestMethod.POST)
	public @ResponseBody Map orderModulePost(Long cusId, String description, Long roomId, String cusName, String cusTel,
			String cusIdCard, String personal, String startTime, String endTime, Integer totalDay, String price,
			String totalPrice, String preferential, boolean needFapiao, String apartmentType) {
//		System.out.println(startTime);
//		System.out.println(startTime+" 12:00");
		//System.out.println(cusId+description+roomId+cusName+cusTel+cusIdCard+personal+startTime+endTime+totalDay+price+totalPrice+preferential+needFapiao+apartmentType);
				Order o = new Order();
		o.setCusId(cusId);
		o.setDescription(description);
		o.setRoomId(roomId);
		o.setCusName(cusName);
		o.setCusTel(cusTel);
		o.setCusIdCard(cusIdCard);
		o.setPersonal(personal);
		try {
			o.setStartTime(DateUtil.parse(startTime+" 12:00", "yyyy-MM-dd hh:mm").getTime());
			o.setEndTime(DateUtil.parse(endTime+" 12:00", "yyyy-MM-dd hh:mm").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.setTime(new Date().getTime());
		o.setTotalDay(totalDay);
		o.setPrice(price);
		o.setTotalPrice(totalPrice);
		o.setPreferential(preferential);
		o.setType(Apartment.getTypeNum(apartmentType));
		o.setStatus(Order.STATUS_ON_PAY);
		o.setNeedFapiao(needFapiao);
		orderservice.add(o);
		Order order = orderservice.get(o.getId());
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("order", order.toMap());
		return info;
	}
	
	@RequestMapping(value = "/changePsd", method = RequestMethod.POST)
	public @ResponseBody Message changePsd(String oldPsd, String psd, int id) {
		String content = customerService.changePsd(oldPsd, psd, id);
		if ("修改成功".equals(content)) {
			customerService.logout();
			return new Message(Constants.MESSAGE_SUCCESS_CODE, content);
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, content);
		}

	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody Message modify(
			long customerId ,String nick,String tel,String idCard,
			String sex,String birthday,String job,
			String education,String declaration,String hobby) {
		Customer customer = customerService.getCustomer(customerId);
		CustomerDetails c = customer.getDetails();
		c.setNick(nick);
		c.setTel(tel);
		c.setIdCard(idCard);
		c.setBirthday(birthday);
		c.setJob(job);
		c.setDeclaration(declaration);
		c.setHobby(hobby); 
		c.setEducation(education);
		System.out.println(c);
		try {
			Customer c1 = customerService.modify(c, customerId);
			session.setAttribute("c", c1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new Message(Constants.MESSAGE_ERR_CODE, "内部错误");
		}
		session.setAttribute(Constants.PAGE, Constants.PAGE_DETAILS);
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "修改成功");
	}
	
	/*
	 * 青舍生活
	 */
	@RequestMapping(value = "/story",method = RequestMethod.POST)
	public @ResponseBody Map storyPage(HttpServletRequest request, int page){
		System.out.println(page);
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
		Map<String, Object> info = new HashMap<String, Object>();
		
		info.put("blogs",pr);
		info.put("sp",sp);
		info.put("ep",ep);
		
		return info;
	}
	@RequestMapping(value = "blog_content", method = RequestMethod.POST)
	public @ResponseBody Map  initBlog(HttpServletRequest request,Long id){
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
	
	/**
	 * 查询微信支付的状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkWechatPay", method = RequestMethod.GET)
	public Message checkWechatOrder(Long id){
		Order o = orderService.get(id);
		JSONObject result = WechatOrderUtils.query(o.getPayNo());
		if("success".equals(result.getString("status")) 
				&& "SUCCESS".equals(result.getString("trade_state"))){
			o.setStatus(Order.STATUS_ON_LEASE);
			orderservice.update(o);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "支付成功");
		}else{
			return new Message(Constants.MESSAGE_ERR_CODE, "支付失败");
		}
	}
	
	/**
	 * 获取用户优惠券
	 * @param uId
	 * @return
	 */
	@RequestMapping("getCoupons")
	@ResponseBody
	public List<Coupon> getCouponsByUser(Long uId){
		return couponService.getCoupon(uId);
	}
	/**
	 * 
	 * @param searchData
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public  Map list(String startTime, String endTime,  Integer area, Integer priceRange, Integer layout,
			Long[] features, Integer  enterTime, Integer leaseType,  String  moreStr,Integer sortType,Integer currentPage) {
		System.out.println("0.0");
		if(null == currentPage){
			currentPage = 1;
		}
		
		SearchForm searchData = new SearchForm();
		searchData.setArea(area);
		searchData.setEndTime(endTime);
		searchData.setSortType(sortType);
		searchData.setPriceRange(priceRange);
		searchData.setLayout(layout);
		searchData.setFeatures(features);
		searchData.setEndTime(endTime);
		searchData.setLayout(layout);
		searchData.setMoreStr(moreStr);
		searchData.setSortType(sortType);
		System.out.println(searchData+"好好好");
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("searchData", searchData);
		info.put("areas", Area.getAreas());
//		info.put("subways", Subway.getSubways());
		info.put("priceRanges", LeasePrice.getPrices());
		info.put("layoutTypes", LayoutType.getLayouts());
		List<Feature> fs = featrueService.listFeatures();
		List<com.xfhotel.hotel.support.Feature> features1 = new ArrayList<com.xfhotel.hotel.support.Feature>();
		for(Feature f:fs){
			features1.add(new com.xfhotel.hotel.support.Feature((int)f.getId(), f.getDescription()));
		}
		com.xfhotel.hotel.support.Feature.setFeatures(features1);
		info.put("features",com.xfhotel.hotel.support.Feature.getFeatures());
		info.put("enterTimes", RoomStatus.getStatusArray());
		info.put("leaseTypes", LeaseType.getLeaseTypes());
		info.put("page",  apartmentService.getApartmentPage(apartmentService.sort(apartmentService.list(),searchData), currentPage));
		
		return info;
	}
	

}
