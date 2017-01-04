package com.xfhotel.hotel.controller;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public String homePage(){
		return "redirect:/admin/login";
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginPage(){
		return "/admin/login";
	}
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public String dashboardPage(){
		return "/admin/dashboard";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user,HttpSession session){
		User u = userService.getUser(user.getUsername(),user.getPassword());
		if(null == u) return "/admin/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "/admin/dashboard";
	}
	
	@RequestMapping(value = "customer_list")
	public String customerList (){
		return "/admin/customer/list";
	}
	
	@RequestMapping(value = "get_customers", method = RequestMethod.POST)
	public @ResponseBody PageResults<Customer> getCustomers (int page){
		HashMap map = new HashMap();
		return customerService.list(page);
	}
	
}
