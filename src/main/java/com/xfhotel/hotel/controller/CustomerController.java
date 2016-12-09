package com.xfhotel.hotel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String tel, String password,HttpSession session) {
		Customer c = customerService.login(tel, password);
		if(c != null){
			session.setAttribute("c", c);
			return "/customer/details";
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CustomerDetails c,HttpSession session,int customerId){
		try {
			Customer c1 = customerService.modify(c,customerId);
			session.setAttribute("c", c1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  "/customer/details";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String reg(String tel, String password,HttpSession session) {
		CustomerDetails details = new CustomerDetails(tel, Constants.DEFAULT_AVATAR);
		Customer c = new Customer(tel, password);
		c.setDetails(details);
		if(customerService.register(c,details) == true){
			session.setAttribute("c", c);
			return "/customer/details";
		}else{
			return "redirect:reg";
		}
	}
	
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String reservationPage(Model model){
		model.addAttribute("title", "我的预约");
		return "/customer/reservation";
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String detailsPage(Model model){
		model.addAttribute("title", "我的资料");
		return "/customer/details";
	}
}
