package com.xfhotel.hotel.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Apply;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.FacilityOrder;
import com.xfhotel.hotel.entity.Fault;
import com.xfhotel.hotel.entity.House;
import com.xfhotel.hotel.entity.Landlord;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Rests;
import com.xfhotel.hotel.entity.Tenant;
import com.xfhotel.hotel.entity.TripOrder;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.ApplyService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.FacilityOrderService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FaultService;
import com.xfhotel.hotel.service.FitnessService;
import com.xfhotel.hotel.service.HouseService;
import com.xfhotel.hotel.service.LandlordService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.RestsService;
import com.xfhotel.hotel.service.TenantService;
import com.xfhotel.hotel.service.TripOrderService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.TimeUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	RestsService restsService;
	
	
	
	
	@Autowired
	TenantService tenantService;
	
	@Autowired
	LandlordService landlordService;
	@Autowired
	TripOrderService tripOrderService;
	@Autowired
	HttpSession session;

	@Autowired
	FacilityService facilityservice;
	
	@Autowired
	FaultService faultservice;
	
	@Autowired
	FacilityOrderService facilityOrderservice;
	
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CouponService couponService;

	@Autowired
	OrderService orderservice;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	BlogService blogService;
	@Autowired
	CleanService cleanService;
	@Autowired
	HouseService houseService;
//	@Autowired
//	VacancyService vacancyService;
	@Autowired
	ApplyService applyService;

	@Autowired
	FitnessService fitnessService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String homePage() {
		return "redirect:/admin/login";
	}
	//..5.8优惠卷新加...
	@RequestMapping(value = "/customer_sendlist", method = RequestMethod.GET)
	public String sendlist() {
		return "/admin/customer/sendlist";
	}
   //..5.8优惠卷结束...
	
	// 7.2 查看评论
	@RequestMapping(value = "/leavemsglist", method = RequestMethod.GET)
	public String leavemsglist() {
	
		return "/admin/apartment/leavemsglist";
	}
	// 7.2 查看评论 end
	// 7.2房态
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String status() {
	
		return "/admin/apartment/status";
	}
	// 7.2房态end
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "/admin/login";
	}
	//..2.28保洁服务...
	@RequestMapping(value = "/customer_baojie", method = RequestMethod.GET)
	public String baojie() {
		List<Clean> list = cleanService.list();
		List<Map> orders = new ArrayList<Map>();
		for (Clean o : list) {
			orders.add(o.toMap());	
			Order order = orderservice.get(o.getOederId());
		}
		session.setAttribute("orders", orders);
		return "/admin/customer/baojie";
	}
	
	//..7.18故障维修...
		@RequestMapping(value = "/customer_weixiu", method = RequestMethod.GET)
		public String Fault() {
			List<Fault> list = faultservice.list();
			List<Map> orders = new ArrayList<Map>();
			for (Fault o : list) {
				orders.add(o.toMap());	
			}
			session.setAttribute("orders", orders);
			return "/admin/customer/weixiu";
		}
	//..7.18添加设施...
		@RequestMapping(value = "/customer_addfacility", method = RequestMethod.GET)
		public String addfacility() {
			List<FacilityOrder> list = facilityOrderservice.list();
			List<Map> orders = new ArrayList<Map>();
			for (FacilityOrder o : list) {
				orders.add(o.toMap());	
			}
			session.setAttribute("orders", orders);
			return "/admin/customer/addfacility";
		}
		@RequestMapping(value = "/customer_collocation", method = RequestMethod.GET)
		public String getApply() {
			List<Apply> list = applyService.list();
			List<Map> orders = new ArrayList<Map>();
			for (Apply o : list) {
				orders.add(o.toMap());	
			}
			List<Landlord> list1 = landlordService.list();
			List<Map> orders1 = new ArrayList<Map>();
			for (Landlord o : list1) {
				orders1.add(o.toMap());	
				
			}
			session.setAttribute("orders", orders1);
			session.setAttribute("ordersd", orders);
			return "/admin/customer/collocation";
		}
		
		@RequestMapping(value = "/customer_statistics", method = RequestMethod.GET)
		public String statistics() {
			return "/admin/customer/statistics";
		}
		@RequestMapping(value = "/customer_houseStatus", method = RequestMethod.GET)
		public String houseStatus() {
			return "/admin/customer/houseStatus";
		}
		
		
	//..7.18叫车服务begin...
		@RequestMapping(value = "/customer_DialogueCar", method = RequestMethod.GET)
		public String DialogueCar() {
			List<TripOrder> list = tripOrderService.list();
			List<Map> orders = new ArrayList<Map>();
			for (TripOrder o : list) {
				orders.add(o.toMap());	
			}
			session.setAttribute("orders", orders);
			return "/admin/customer/DialogueCar";
		}			
	//..7.18叫车服务end...
//		@RequestMapping(value = "/customer_collocation", method = RequestMethod.GET)
//		public String collocation() {
//			List<Clean> list = cleanService.list();
//			List<Map> orders = new ArrayList<Map>();
//			for (Clean o : list) {
//				orders.add(o.toMap());	
//				Order order = orderservice.get(o.getOederId());
//			}
//			session.setAttribute("orders", orders);
//			return "/admin/customer/collocation";
//		}								
	
		//成员权限 BEGIN
		@RequestMapping(value = "/customer_manager", method = RequestMethod.GET)
		public String collocation() {
			List<User> list = userService.list();
			List<Map> orders = new ArrayList<Map>();
			for (User o : list) {
				orders.add(o.toMap());	
			}
			session.setAttribute("orders", orders);
			return "/admin/customer/manager";
		}	
		
		// 成员权限 END

		//合作商户 BEGIN 9-1
		@RequestMapping(value = "/customer_jointwork", method = RequestMethod.GET)
		public String jointwork() {
			List<Tenant> list = tenantService.list();
			List<Map> orders = new ArrayList<Map>();
			for (Tenant o : list) {
				orders.add(o.toMap());	
			}
			session.setAttribute("orders", orders);
			return "/admin/customer/jointwork";
		}
		// 合作商户 END 
		
		
		
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardPage() {
		session.setAttribute("apartmentCount", apartmentService.list().size());
		session.setAttribute("orderCount", orderservice.list(Order.CATEGOTY_ALL).size());
		session.setAttribute("customerCount", customerService.list().size());
		session.setAttribute("blogCount", blogService.list().size());
		return "/admin/dashboard";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String customerPage() {
		return "/admin/customer/customer";
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String orderPage() {
//		List<Order> list = orderservice.list(Apartment.TYPE_HOTEL);
//		List<Map> orders = new ArrayList<Map>();
//		for (Order o : list) {
//			orders.add(o.toMap());
//		}
//		session.setAttribute("orders", orders);
		return "/admin/order/order";
	}
	@RequestMapping(value = "/order_page", method = RequestMethod.POST)
	public  @ResponseBody Map order1(int page) {
		
		PageResults<Order> pr = orderservice.listPage(page);
		List cl = new ArrayList();
		for(Order c:pr.getResults()){
			cl.add(c.toMap());
		}
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
		if( (ep-Constants.pagesize+1) < ep ){
			sp = ep-Constants.pagesize+1;
			if( sp<1 )
				sp=1;
		}
		Map map = new HashMap();
		map.put("results",cl);
		map.put("currentPage",pr.getCurrentPage());
		map.put("pageCount", pr.getPageCount());
		map.put("sp",sp);
		map.put("ep", ep);
		return map;
	}
	
	@RequestMapping(value = "/system", method = RequestMethod.GET)
	public String systemPage() {
		
		return "/admin/system";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Message login(String userName,String password){
		User u = userService.login(userName, password);
		if (u != null) {
			User c = new User();
			c.setId(u.getId());
			c.setAuthority(u.getAuthority());
			c.setUsername(u.getUsername());
			c.setDate(u.getDate());
			c.setStatus(u.getStatus());
			u.setDate(new Date().getTime());
			userService.update(u);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, c);
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, "账号或密码错误");
		}
	}
	
	@RequestMapping(value = "customer_list")
	public String customerList (){
		return "/admin/customer/list";
	}
	
	@RequestMapping(value = "get_customers", method = RequestMethod.POST)
	public @ResponseBody Map getCustomers (int page){
		PageResults<Customer> pr = customerService.list(page);
		List cl = new ArrayList();
		for(Customer c:pr.getResults()){
			cl.add(c.toMap());
		}
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
		if( (ep-Constants.pagesize+1) < ep ){
			sp = ep-Constants.pagesize+1;
			if( sp<1 )
				sp=1;
		}
		Map map = new HashMap();
		map.put("results",cl);
		map.put("currentPage",pr.getCurrentPage());
		map.put("pageCount", pr.getPageCount());
		map.put("sp",sp);
		map.put("ep", ep);
		return map;
	}
	
	@RequestMapping(value = "change_status", method = RequestMethod.POST)
	public @ResponseBody String changeStatus (long id, int status){
		customerService.changeStatus(id,status);
		return "1";
	}
	
	@RequestMapping(value = "view_customer")
	public String viewCustomer (HttpServletRequest request, long id){
		request.getSession().setAttribute("c", customerService.getCustomer(id));
		return "/admin/customer/details";
	}
//	@RequestMapping(value = "Vacancy")
//	public Message addVacancy( long id ,long data ,long apartmentId,int state){
//		Vacancy vacancy = vacancyService.getVacancy(apartmentId, data);
//		try{
//		if(vacancy!=null){
//			vacancy.setState(state);
//			vacancyService.update(vacancy);
//		}else{
//			Vacancy vacancy1 = new Vacancy();
//			vacancy1.setApartmentId(apartmentId);
//			vacancy1.setData(data);
//			vacancy1.setState(state);
//			vacancyService.add(vacancy1);
//		}
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		return new Message(Constants.MESSAGE_ERR_CODE, "设置失败");
//		}
//		return new Message(Constants.MESSAGE_SUCCESS_CODE, "设置成功");
//	}
//	
//	public String login(User user) {
//		User u = userService.getUser(user.getUsername(), user.getPassword());
//		if (null == u)
//			return "/admin/login";
//		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
//		return "/admin/dashboard";
//	}
	@RequestMapping(value = "/sendlist", method = RequestMethod.POST)
	public @ResponseBody Message sendlist(String startTime,String endTime,int type,Double cValue,String rule,Long Id[]) {
		try {
			List<Long> list = Arrays.asList(Id);
			for(Long uId : list){
				Coupon coupon = new Coupon();
				coupon.setcValue(cValue);
				coupon.setStartTime(TimeUtil.getDateLong(startTime));
				coupon.setEndTime(TimeUtil.getDateLong(endTime));
				coupon.setType(type);
				coupon.setRule(rule);
				coupon.setuId(uId);
				couponService.add(coupon);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
	}
	@RequestMapping(value = "/dsendlist", method = RequestMethod.POST)
	public @ResponseBody Message dsendlist(Double money , String sex , Double time){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(money!=null){
				List<Customer> c = customerService.list();
				for(Customer customer : c){
					if(customer.getConsumptionCount()>money){
						map.put(customer.getTel(), customer);	
					}
				}
			}else if(sex!=null){
				List<CustomerDetails> cd = customerService.getlist();
				
				for(CustomerDetails customer2 : cd){
					if(customer2.getSex()!=null){
						if(customer2.getSex().equals(sex)){
							Customer customer=  customerService.getCustomer(customer2.getId());
							map.put(customer.getTel(), customer);	
						}
					}
				}
			}else if(time!=null){
				List<Customer> c = customerService.list();
//				System.out.println(time+"'0.0.'");
				if(time==1){
					for (Customer customer : c) {
						 Calendar calendar = Calendar.getInstance();
					        Date date = new Date(System.currentTimeMillis());
					        Date time1 = new Date(customer.getRegTime());
					        calendar.setTime(date);
//					        calendar.add(Calendar.WEEK_OF_YEAR, -1);
					        calendar.add(Calendar.YEAR, -1);
					        date = calendar.getTime();
//					        System.out.println(time1.before(date));
						if(time1.before(date)||time1.equals(date)){
//							System.out.println(ddd+customer.getRegTime());
							map.put(customer.getTel(), customer);
							
						}
				    }
				}else{
//					System.out.println("00dsd");
					for (Customer customer : c) {
						 Calendar calendar = Calendar.getInstance();
					        Date date = new Date(System.currentTimeMillis());
					        Date time1 = new Date(customer.getRegTime());
					        calendar.setTime(date);
					        calendar.add(Calendar.WEEK_OF_YEAR, -1);
//					        calendar.add(Calendar.YEAR, -1);
					        date = calendar.getTime();
//					        System.out.println(time1.after(date));
						if(time1.after(date)||time1.equals(date)){
							map.put(customer.getTel(), customer);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
			}
		 ArrayList<Object> list = new ArrayList<Object>();
		  for(String key : map.keySet()){
		   list.add(map.get(key));
		  }
		return new Message(Constants.MESSAGE_SUCCESS_CODE, list);
	}
	
	@RequestMapping(value = "/price", method = RequestMethod.POST)
	public @ResponseBody Message price(Long[] time ,Long apartmentId,Double price){
		try{
			for(int i=0;i<time.length;i++){
				Long date = time[i];
				Price price1 = apartmentService.getSpPrice(apartmentId, date-1000*60*60*12);
				if(price1!=null){
					price1.setPrice(price);
					apartmentService.setSpPrice(price1);
				}else{
					Price price2 = new Price();
					price2.setApartment_id(apartmentId);
					price2.setDate(date-1000*60*60*12);
					price2.setPrice(price);
					apartmentService.setSpPrice(price2);
			}
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "设置失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "设置成功");
	}
	
	@RequestMapping(value = "/house", method = RequestMethod.POST)
	public @ResponseBody Message addHouse(Long[] time,Long apartmentId,int state){
		try{
			List<House> houses = houseService.list();
			for(House houses1:houses){
				Long date = houses1.getDate();
				if(date<DateUtil.getStartTime()+1000*60*60*12){
					houseService.delete(houses1);
				}
			}
			for(int i=0;i<time.length;i++){
				Long data=time[i];
				House house = houseService.getHouse(apartmentId, data);
				if(house!=null){
					house.setState(state);
					houseService.update(house);
				}else{
					House house1 = new House();
					house1.setApartmentId(apartmentId);
					house1.setDate(data);
					house1.setState(state);
					houseService.add(house1);
				}
			}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "设置失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "设置成功");
	}
	private long dateToLong(Date date) {
		// TODO Auto-generated method stub
		return 0;
	} 
	
	@RequestMapping(value = "/Coupon", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  getCoupon( ){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Coupon> coupon1 = couponService.list();
		ArrayList<Object> list1 = new ArrayList<Object>();
		ArrayList<Object> list2= new ArrayList<Object>();
		double sun =0;
		double figure= 0;
		map.put("sumTotal", coupon1.size());//总数量
		if(coupon1.size()==0){
			map.put("money", 0);
			map.put("stale", 0);
			map.put("used", 0);
			map.put("unused", 0);
			map.put("usedMoney", 0);
		}
		for(Coupon coupon2:coupon1){
			double jing = coupon2.getcValue();
			sun +=jing;
			map.put("money", sun);//总金额
			long endTime = TimeUtil.getDateLong(coupon2.getEndTime());
			long time = new Date().getTime();
//			System.out.println(time + endTime);
			boolean usable = coupon2.isUsed();
			if(time>=endTime){
				list1.add(coupon2);
				map.put("stale", list1.size());//过期
			}
			if(usable!=true){
				 figure+=coupon2.getcValue();
				list2.add(coupon2);
				map.put("used", list2.size());//未使用
//				couponService.delete(couponService.getCoupon2(coupon2.getId()));
			}
			map.put("usedMoney",  sun-figure);
			map.put("unused", coupon1.size()-list2.size());//使用
		}
		if(list1.size()==0){
			map.put("stale", 0);
		} else if(list2.size()==0){
			map.put("used", 0);
		}
			return map;
	}

	@RequestMapping(value = "/getCouponsId", method = RequestMethod.POST)
	@ResponseBody
	public List<Coupon>  getCouponsByUser(Long uId ){
			return couponService.getCoupon(uId);
	}
	
	@RequestMapping(value = "/getClean", method = RequestMethod.POST)
	@ResponseBody
	public Message getClean(Long oederId ){
		try {
			if(oederId==null){
				System.out.println();
				return new Message(Constants.MESSAGE_ERR_CODE, "为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "失败");
			}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, cleanService.getClean2(oederId));
	}
	
	@RequestMapping(value = "/roomSort", method = RequestMethod.POST)
	public @ResponseBody Message  roomSort(Long roomId , int sort){
		try{
			Apartment apartment = apartmentService.findById(roomId);
			apartment.setSort(sort);
			apartmentService.update(apartment);
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "设置失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,"设置成功");
	}
	@RequestMapping(value = "/TenantAdd", method = RequestMethod.POST)
	public @ResponseBody Message  TenantAdd(String userName,String password ,String tradeName,String tel){
		try{
			List<Tenant>  r = tenantService.list();
			Tenant t = new Tenant();
			t.setTel(tel);
			t.setUserName(userName);
			t.setTradeName(tradeName);
			t.setPassword(password);
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d =sdf.format(new Date().getTime());
			t.setData(d);
			t.setVariety(r.size());
			tenantService.add(t);
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "设置失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,"设置成功");
	}
	
	@RequestMapping(value = "/TenantD", method = RequestMethod.POST)
	public @ResponseBody Message TenantD(Long id){
		try{
			Tenant tenant = tenantService.findById(id);
			tenantService.delete(tenant);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
			}		
		
	}

	@RequestMapping(value = "/Rests", method = RequestMethod.POST)
	public @ResponseBody Message Rests(String source,String startTime, String endTime,Double sum,Long tel,int fate,String name,Long apId) {
		try {
			Apartment apartment = apartmentService.findById(apId);
			String roomName = apartment.getPosition().getString("xiao_qu")+apartment.getPosition().getString("men_pai");
			Rests rests = new Rests();	
			rests.setApId(apId);
			rests.setEndTime(endTime);
			rests.setRoomName(roomName);
			rests.setName(name);
			rests.setTel(tel);
			rests.setStartTime(startTime);
			rests.setSource(source);
			rests.setFate(fate);
			rests.setSum(sum);
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d =sdf.format(new Date().getTime());
			rests.setTime(d);
			restsService.add(rests);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
	}
	
	@RequestMapping(value = "/deleteRests", method = RequestMethod.POST)
	public @ResponseBody Message deleteRests(Long id){
			try{
				Rests rests = restsService.findById(id);
				restsService.delete(rests);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");
	}
	
	@RequestMapping(value = "/getListRests", method = RequestMethod.POST)
	public @ResponseBody Message getListRests(int page){
			try{
				PageResults<Rests> rests = restsService.getRests(page);
				return new Message(Constants.MESSAGE_SUCCESS_CODE, rests);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
		}
	}
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	public @ResponseBody Message getData(){
			try{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("room",apartmentService.list().size());
				map.put("order", orderservice.list(0).size());
				map.put("customer", customerService.list().size());
				map.put("blog", blogService.list().size());
				map.put("coupon", couponService.list().size());
				map.put("clean", cleanService.list().size());
				map.put("fault", faultservice.list().size());
				map.put("facility", facilityOrderservice.list().size());
				map.put("trip", tripOrderService.list().size());
				map.put("landlord", landlordService.list().size());
				map.put("user", userService.list().size());
				map.put("tenant", tenantService.list().size());
				return new Message(Constants.MESSAGE_SUCCESS_CODE, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
		}
	}
	@RequestMapping(value = "/house1", method = RequestMethod.POST)
	public @ResponseBody Message addHouse1(Long startDate , Long endDate,Long apartmentId,int state){
		try{
			List<House> houses = houseService.list();
			for(House houses1:houses){
				Long date = houses1.getDate();
				if(date<DateUtil.getStartTime()+1000*60*60*12){
					houseService.delete(houses1);
				}
			}
			Long  day=((endDate-startDate)/1000/60/60/24);
			Long data=startDate;
			for(int i=0;i<=day;i++){
				House house = houseService.getHouse(apartmentId, data);
				if(house!=null){
					house.setState(state);
					houseService.update(house);
					data+=(long) (1000*60*60*24);
				}else{
					House house1 = new House();
					house1.setApartmentId(apartmentId);
					house1.setDate(data);
					house1.setState(state);
					houseService.add(house1);
					data+=(long) (1000*60*60*24);
			}
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "设置失败");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "设置成功");
	}
	
}


