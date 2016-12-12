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

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("c");
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String tel, String password,Model model) {
		Customer c = customerService.login(tel, password);
		if (c != null) {
			session.setAttribute("c", c);
			session.setAttribute(Constants.PAGE, Constants.PAGE_RESERVATION);
			return "redirect:/customer/reservation";
		} else {
			model.addAttribute("msg", "用户或密码错误");
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CustomerDetails c, int customerId) {
		try {
			Customer c1 = customerService.modify(c, customerId);
			session.setAttribute("c", c1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute(Constants.PAGE, Constants.PAGE_DETAILS);
		return "/customer/details";
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String reg(String tel, String password, Model model) {
		if (customerService.checkTel(tel)) {
			model.addAttribute("msg", "手机号已使用");
			return "redirect:reg";
		}
		CustomerDetails details = new CustomerDetails(tel, Constants.DEFAULT_AVATAR);
		Customer c = new Customer(tel, password);
		c.setDetails(details);
		if (customerService.register(c, details) == true) {
			session.setAttribute("c", c);
			setPage(model, "我的预约", Constants.PAGE_RESERVATION);
			return "redirect:/customer/reservation";
		} else {
			return "redirect:reg";
		}
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String reservationPage(Model model) {
		setPage(model, "我的预约", Constants.PAGE_RESERVATION);
		return "/customer/reservation";
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String detailsPage(Model model) {
		setPage(model, "我的资料", Constants.PAGE_DETAILS);
		return "/customer/details";
	}

	@RequestMapping(value = "/change_password", method = RequestMethod.GET)
	public String changePasswordPage(Model model) {
		setPage(model, "修改密码", Constants.PAGE_CHANGE_PWD);
		return "/customer/change-password";
	}

	@RequestMapping(value = "/check_tel", method = RequestMethod.GET)
	public @ResponseBody boolean checkTel(String tel) {
		return customerService.checkTel(tel);
	}

	/**
	 * 显示页面
	 * 
	 * @param title
	 * @param page
	 */
	private void setPage(Model model, String title, String page) {
		model.addAttribute("title", title);
		session.setAttribute(Constants.PAGE, page);
	}

}
