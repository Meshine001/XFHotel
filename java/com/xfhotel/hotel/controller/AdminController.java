package com.xfhotel.hotel.controller;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;

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
	FeatureService featureService;
	@Autowired
	FacilityService facilityService;
	@Autowired
	OrderService orderservice;
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	BlogService blogService;
	

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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "/admin/login";
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
		List<Order> list = orderservice.list(Apartment.TYPE_HOTEL);
		List<Map> orders = new ArrayList<Map>();
		for (Order o : list) {
			orders.add(o.toMap());
		}
		session.setAttribute("orders", orders);
		return "/admin/order/order";
	}

	@RequestMapping(value = "/system", method = RequestMethod.GET)
	public String systemPage() {
		session.setAttribute("features", featureService.listFeatures());
		session.setAttribute("facilities", facilityService.listFacilities());
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
	
	public String login(User user) {
		User u = userService.getUser(user.getUsername(), user.getPassword());
		if (null == u)
			return "/admin/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "/admin/dashboard";
	}

}
