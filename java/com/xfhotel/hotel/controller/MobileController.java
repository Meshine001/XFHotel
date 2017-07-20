package com.xfhotel.hotel.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.House;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.service.HouseService;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.SystemConfService;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@RequestMapping("mobile")
public class MobileController  {
 	@Autowired
	CleanService cleanservice;
	
	@Autowired
	LockService lockService;
	
	@Autowired
	FileService fileService;

	@Autowired
	ApartmentService apartmentService;

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

	@Autowired
	SystemConfService systemConfiService;
	
	@Autowired
	HouseService houseService;
	

	/**
	 * 房屋
	 * @return
	 */
	@RequestMapping(value = "/home",method = RequestMethod.POST)
	public @ResponseBody Map home(){
		JSONArray homeRooms = apartmentService.getHomeApartments();
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("homeRooms",homeRooms);
		return info;	
	}
	
	@RequestMapping(value = "/homeTherefore",method = RequestMethod.POST)
	public @ResponseBody Map homeTherefore(){
		JSONArray homeRooms = apartmentService.getHomeApartments1();
		Map<String,Object> info = new HashMap<String, Object>();
		info.put("homeRooms",homeRooms);
		return info;	
	}
	
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public @ResponseBody JSONObject info(Long apartmentId){
		return apartmentService.getApartmentById(apartmentId);
	}
	
	/**
	 * 登录
	 * @param tel
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody  Message login(String tel, String password) {
		Customer c = customerService.login(tel, password);
		if (c != null) {
			return new Message(Constants.MESSAGE_SUCCESS_CODE, c);
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, "账号或密码错误");
		}
	}
	
	/**
	 * 修改密码
	 * @param tel
	 * @param psd
	 * @return
	 */
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

	/**
	 * 注册
	 * @param tel
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public @ResponseBody Message reg(String tel, String password) {
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
			Customer c1 = customerService.getCustomer(c.getId());
			Calendar calendar = Calendar.getInstance();
	        Date date = new Date(System.currentTimeMillis());
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH, +6);
	        date = calendar.getTime();
			List<Double> list = new ArrayList<Double>();
			list.add(20.0);
			list.add(30.0);
			list.add(50.0);
			List<String> list1 = new ArrayList<String>();
			list1.add("200");
			list1.add("300");
			list1.add("500");
			int d = 0;
			for(double cValue : list){
				for(int i =0;list1.size()>i;i++){
					String rule = list1.get(d);
					Coupon coupon = new Coupon();
					coupon.setcValue(cValue);
					coupon.setStartTime(new Date().getTime());
					coupon.setEndTime(date.getTime());
					coupon.setType(1);
					coupon.setRule(rule);
					coupon.setuId(c1.getId());
					couponService.add(coupon);
					d++;
					break;
				}
			}
			return new Message(Constants.MESSAGE_SUCCESS_CODE, c1.getId());
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "注册失败");
	}
	
	
/**
 *
 * @param roomId
 * @param page
 * @return
 */
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Object> getRoomComments(Long roomId){
		ArrayList<Object> list = new ArrayList<Object>();
		List<Comment> comment = commentService.getCommentsByRoom(roomId);
		for(Comment comment1:comment){
			Customer customer = customerService.getCustomer(comment1.getFromWho());
			CustomerDetails f = customer.getDetails();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tel", customer.getTel());
			map.put("comment", comment1);
			map.put("Avatar", f.getAvatar());
			list.add(map);
		}
		return list;
	}
	
	
	/**
	 * 获取价格
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value = "/getRoomRates", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRoomRates(Long roomId){
		return commentService.getRoomRates(roomId);
	}
	
	
	/**
	 * 验证短信验证码
	 * @param tel
	 * @param vCode
	 * @return
	 */
	@RequestMapping(value="/checkVCode")  
	public @ResponseBody Message checkVCode(String tel,String vCode){
		if(tel==null||vCode==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
		}
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
//			System.out.println("12365");
			return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
		}
//		System.out.println("123");
		return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
	}
	
	/**
	 * 请求发送注册手机验证码
	 * @param tel
	 * @return
	 */
	@RequestMapping(value="/sendVCode")  
	public @ResponseBody Message sendTelValidateCode(String tel){
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
			System.out.println("send vcode==>"+vCode);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "获取验证码失败");
		}
		
	}
	
	/**
	 * 触发订单
	 * @param startTime
	 * @param endTime
	 * @param apartmentId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/module", method = RequestMethod.POST)
	public  @ResponseBody JSONObject orderModule(String startTime, String endTime, Long apartmentId ,Long id) throws Exception {
		
		return apartmentService.createOrderMoudle(startTime, endTime, apartmentId);
	}
	
	/**
	 * 查询房屋状态
	 * @param roomId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
	public @ResponseBody Message checkAvailable(Long roomId, String startTime, String endTime) {
		Long start = TimeUtil.getDateLong(startTime);
		Long start1 = TimeUtil.getDateLong(endTime);
		try {
			List<House> house = houseService.getHouseId(roomId);
			for(House house1 : house){
				if(start<house1.getDate()&&start1>house1.getDate()&&house1.getState()==0){
					ArrayList<Object> list = new ArrayList<Object>();
					list.add(1);
					return new Message(Constants.MESSAGE_SUCCESS_CODE, list);
				}
			}
			List<Order> availableOders = orderservice.checkAvailable(roomId, startTime, endTime);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, availableOders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查询失败");
		}
	}

	
	/**
	 * 获取房源
	 * @param cId
	 * @param category
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody Message search(Long cId, int category, int type, String startDate, String endDate, int range)
	{
		try {
			List<Order> orders = orderservice.search(cId, category, type, startDate, endDate, range);
			List<Map> maps = new ArrayList<Map>();
			for (Order o : orders) {
				Map m = o.toMap();
				Map apartment = apartmentService.getApartmentById(o.getRoomId());
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
	 * @param cusId
	 * @param description
	 * @param roomId
	 * @param cusName
	 * @param cusTel
	 * @param otherCusName
	 * @param otherCusIdCard
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
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/modulePost", method = RequestMethod.POST)
	public @ResponseBody Map orderModulePost(Long cusId, String description, Long roomId, String cusName, String cusTel,
			String otherCusName[], String otherCusIdCard[], String cusIdCard, String personal, String startTime,
			String endTime, Integer totalDay, String price, String totalPrice, String preferential, boolean needFapiao,
			String apartmentType,String id) throws ParseException {
		Long couponId = null;
		try {
			couponId = Long.valueOf(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		Order order = orderService.postOrder(cusId, description, roomId, cusName, cusTel, otherCusName, otherCusIdCard, cusIdCard, personal, startTime, endTime, totalDay, price, totalPrice, preferential, needFapiao, apartmentType, couponId);
		Map<String, Object> info = new HashMap<String, Object>();
		Long data = DateUtil.parse(startTime + " 12:00", "yyyy-MM-dd HH:mm").getTime();
		House house = houseService.getHouse(roomId, data);
		int state =0;
		if(house!=null){
			house.setState(state);
			houseService.update(house);
		}else{
			House house1 = new House();
			house1.setApartmentId(roomId);
			house1.setDate(data);
			house1.setState(state);
			houseService.add(house1);
		}
//		System.out.println(info);
		return info;
	}
	
	
	/**
	 * 修改密码
	 * @param oldPsd
	 * @param psd
	 * @param id
	 * @return
	 */
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
	
	
	/**
	 * 修改个人资料
	 * @param customerId
	 * @param nick
	 * @param tel
	 * @param idCard
	 * @param sex
	 * @param birthday
	 * @param job
	 * @param education
	 * @param declaration
	 * @param hobby
	 * @param avatar
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody Message modify(
			long customerId ,String nick,String tel,String idCard,
			String sex,String birthday,String job,
			String education,String declaration,String hobby,String avatar) {
		Customer customer = customerService.getCustomer(customerId);
		CustomerDetails c = customer.getDetails();
		c.setNick(nick);
		c.setSex(sex);
		c.setTel(tel);
		c.setIdCard(idCard);
		c.setBirthday(birthday);
		c.setJob(job);
		c.setDeclaration(declaration);
		c.setHobby(hobby); 
		c.setEducation(education);
		c.setAvatar(avatar);
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
//		System.out.println(page);
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
	
	/**
	 * 青客生活
	 * @param request
	 * @param id
	 * @return
	 */
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
	 * 获取可用优惠卷
	 * @param uId
	 * @param totalPrice
	 * @return
	 */
	@RequestMapping("getMyCoupons")
	@ResponseBody
	public ArrayList<Object> getMyCoupons(Long uId ,Double price){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Coupon> coupon = couponService.getCoupon(uId);
		for(Coupon coupon2:coupon){
			long startTime = TimeUtil.getDateLong(coupon2.getStartTime());
			long endTime = TimeUtil.getDateLong(coupon2.getEndTime());
			Double rule = Double.valueOf(coupon2.getRule()); 
			Double yf = Double.valueOf(coupon2.getcValue());
			long time = new Date().getTime();
			boolean usable = coupon2.isUsed();
			if(startTime<=time&&time<=endTime&&price>=rule&&usable!=true&&price>yf){
				map.put(String.valueOf(coupon2.getId()), coupon2);
			}
		}
		
		ArrayList<Object> list = new ArrayList<Object>();
		  for(String key : map.keySet()){
		   list.add(map.get(key));
		  }
		return list;
	}
	
	/**
	 * 
	 * 获取用户优惠券
	 * @param uId
	 * @return
	 */
	@RequestMapping("getCoupons")
	@ResponseBody
	public ArrayList<Object>  getCouponsByUser(Long uId ){
		List<Coupon> coupon1 = couponService.getCoupon(uId);
		ArrayList<Object> list = new ArrayList<Object>();
		for(Coupon coupon2:coupon1){
			long endTime = TimeUtil.getDateLong(coupon2.getEndTime());
			long time = new Date().getTime();
//			System.out.println(time + endTime);
			boolean usable = coupon2.isUsed();
			if(time<=endTime&&usable==false){
				list.add(coupon2);
//				couponService.delete(couponService.getCoupon2(coupon2.getId()));
			}
		}
			return list;
	}
	
	
/**
 * 获取房屋详情
 * @param searchData
 * @param currentPage
 * @return
 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody Map list(Integer currentPage,int priceRange , int area,int layout, Long[] features ,String endTime ,
			String startTime,Integer enterTime , Integer leaseType,
			String moreStr,Integer sortType) {
		SearchForm searchData = new SearchForm();
		searchData.setArea(area);
		searchData.setLayout(layout);
		searchData.setFeatures(features);
		searchData.setEndTime(endTime);
		searchData.setEnterTime(enterTime);
		searchData.setLeaseType(leaseType);
		searchData.setMoreStr(moreStr);
		searchData.setPriceRange(priceRange);
		searchData.setSortType(sortType);
		searchData.setStartTime(startTime);
		if(null == currentPage){
			currentPage = 1;
		}
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("searchData", searchData);
		info.put("areas", Area.getAreas());
//		info.put("subways", Subway.getSubways());
		info.put("priceRanges", LeasePrice.getPrices());
		info.put("layoutTypes", LayoutType.getLayouts());
		info.put("enterTimes", RoomStatus.getStatusArray());
		info.put("leaseTypes", LeaseType.getLeaseTypes());
		info.put("page",  apartmentService.getApartmentPage(apartmentService.sort(apartmentService.list(),searchData), currentPage));
		return info;	
	}
	
/**
 * 
 * @param file
 * @param request
 * @return
 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Message upload(MultipartFile file, HttpServletRequest request) {
		if (file != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(request.getSession().getServletContext().getRealPath("/"));
//			System.out.println(sb.toString());
			String fullPath = fileService.saveFile(file, sb.toString());
			if (fullPath != null)
				return new Message(Constants.MESSAGE_SUCCESS_CODE, fullPath);
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "上传失败");
	}
	
	/**
	 * 查看密码
	 * @param request
	 * @param oId
	 * @return
	 */
	@RequestMapping( value = "/viewpassword", method = RequestMethod.POST)
	public @ResponseBody Map viewPassword(Long oId) {
		Order order = orderService.get(oId);
		JSONObject a = apartmentService.getApartmentById(order.getRoomId());
		JSONObject basic =a.getJSONObject("basic_info");
		String phone = order.getCusTel();
		String lock_no = basic.getString("suo_di_zhi");
		 lockService.viewPassword(phone, lock_no);
		Map< String, Object> map = new HashMap<String, Object>();
		map.put("pwd_text",  lockService.viewPassword(phone, lock_no));
		return map ;
	}
	
	/**
	 * 退租
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/outLease", method = RequestMethod.POST)
	public @ResponseBody Message outLease(Long orderId) {
		return orderservice.outLease(orderId);
	}
	
	@RequestMapping(value = "/Clean", method = RequestMethod.POST)
	public @ResponseBody  Message Clean(Long uId ,int type){
		if(uId==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "无订单"); 
		}
		System.out.println(type);
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
	  return new Message(Constants.MESSAGE_SUCCESS_CODE, list);	
	}
	
	
	@RequestMapping(value = "/cleanAdd", method = RequestMethod.POST)
	public @ResponseBody Message cleanAdd (String demand,Long oederId , int content1[],int cleanTime) {
		if(content1==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "请选择服务内容");
		}
		try {
 			Order o = orderservice.get(oederId);
			Clean clean = new Clean();
			clean.setDemand(demand);
			clean.setCleanTime(Clean.getcleanTime(cleanTime));
			clean.setContent(Clean.getTypeDescription(content1));
			clean.setRoomId(o.getRoomId());
			clean.setOederId(oederId);
			clean.setTime(new Date().getTime());
			clean.setStatus(Clean.STATUS_NOT_AFFIRM);
			cleanservice.add(clean);
//			TODO
//			发短信给管理员
//			【青舍都市】您有新订单需要确认，请及时处理。{1}
			JSONObject a = apartmentService.getApartmentById(o.getRoomId())
					.getJSONObject("position");
			String f= a.getString("xiao_qu")+a.getString("lou_hao")+"号楼"+
					a.getString("dan_yuan")+"单元"+a.getString("lou_ceng")+"层"+a.getString("men_pai")+"号";
			String[] p = {f};
			SendTemplateSMS.sendSMS(Constants.SMS_INFORM_COMFIRM_CLEAN_ORDER, systemConfiService.getConfig().getSms(), p);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		return new Message(Clean.STATUS_NOT_AFFIRM, "等待管理员确认");
	}
	
	
	
	/*
	 * 获取订单
	 */
	@RequestMapping(value = "/getOrder", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Object> getOrder(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Order order = orderservice.get(id);
		String  i= TimeUtil.getDateStr(order.getStartTime());
		String  d= TimeUtil.getDateStr(order.getEndTime());
		map.put("开始时间", i);
		map.put("结束时间", d);
		map.put("全部", order);
		ArrayList<Object> list = new ArrayList<Object>();
		  for(String key : map.keySet()){
		   list.add(map.get(key));
		  }
		return list;
	}
	
	@RequestMapping(value = "/getIntegral", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getIntegral(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Customer customer = customerService.getCustomer(id);
		map.put("integral", customer.getConsumptionCount());
		return map;
	}
	
	@RequestMapping(value = "/price/{id}/{startDate}", method = RequestMethod.POST)
	public @ResponseBody JSONObject getRangePrices(@PathVariable("id") Long id,
			@PathVariable("startDate") String startDate) {
		JSONArray prices = apartmentService.get2MonthPrices(id, startDate);
		JSONObject data = new JSONObject();
		for (Object obj : prices) {
			String key = (String) JSONObject.fromObject(obj).keys().next();
			data.put(key, JSONObject.fromObject(obj).get(key));
		}
		return data;
	}
	
	
	@RequestMapping(value = "/distance",method = RequestMethod.POST)
	public @ResponseBody ArrayList<Object> distance(double lat1 , double lng1 ,Long mi){
		JSONArray homeRooms = apartmentService.getHomeApartments1();
		ArrayList<Object> list = new ArrayList<Object>();
	     for (int i = 0; i < homeRooms.size(); i++) {
	    		Map<String, Object> map = new HashMap<String, Object>();
	            JSONObject jo = (JSONObject) homeRooms.get(i);
				String a1 = jo.getJSONObject("position").getString("jing_du");
				String a2 = jo.getJSONObject("position").getString("wei_du");
				  double lng2=Double.parseDouble(a1);
				  double lat2=Double.parseDouble(a2);
				  if(apartmentService.GetDistance(lat1,lng1,lat2,lng2)<=mi){
					  map.put("apartment", jo);
					  map.put("distance", apartmentService.GetDistance(lat1,lng1,lat2,lng2));
					  list.add(map); 
				  }
				  
	     }
		return list;	
	}

	public static void main(String[] args) {
//		long date = new Date().getTime(); 
//		Long res = 0L;
//		  Date date = new Date(System.currentTimeMillis());
//		Calendar calendar = Calendar.getInstance();//日历对象
//		calendar.setTime(date);
//		 calendar.add(Calendar.MONTH, -6);//月份减一为-1，加一为1
//		 res=calendar.getTime().getTime();
//		 //System.out.println(sdf.format());
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sd.format(date));
//		System.out.println();
//		  Date date = new Date(System.currentTimeMillis());
        
	}
}

