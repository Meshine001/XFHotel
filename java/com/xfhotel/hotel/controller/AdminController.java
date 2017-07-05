package com.xfhotel.hotel.controller;
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
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.House;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.HouseService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.TimeUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	HttpSession session;

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
	public String login(User user,HttpSession session){
		User u = userService.getUser(user.getUsername(),user.getPassword());
		if(null == u) return "/admin/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "redirect:/admin/dashboard";
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
	public String login(User user) {
		User u = userService.getUser(user.getUsername(), user.getPassword());
		if (null == u)
			return "/admin/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "/admin/dashboard";
	}
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
	
	@RequestMapping(value = "/house", method = RequestMethod.POST)
	public @ResponseBody Message addHouse(Long data ,Long apartmentId,int state){
		House house = houseService.getHouse(apartmentId, data);
		try{
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
		map.put("sumTotal", coupon1.size());//总数量
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
				list2.add(coupon2);
				map.put("unused", list2.size());//未使用
//				couponService.delete(couponService.getCoupon2(coupon2.getId()));
			}
			map.put("fresh", coupon1.size()-list1.size());//未过期
			map.put("used", coupon1.size()-list2.size());//使用
		}
			return map;
	}
	@RequestMapping(value = "/getCouponsId", method = RequestMethod.POST)
	@ResponseBody
	public List<Coupon>  getCouponsByUser(Long uId ){
			return couponService.getCoupon(uId);
	}

}
