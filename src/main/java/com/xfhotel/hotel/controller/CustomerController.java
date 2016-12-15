package com.xfhotel.hotel.controller;

import java.util.Date;

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
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	HttpSession session;

	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loginRegPage(String forword,Model model) {
		model.addAttribute("forword", forword);
		return "/customer/login-reg";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("c");
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Message login(String tel, String password, Model model) {
		Customer c = customerService.login(tel, password);
		if (c != null) {
			session.setAttribute("c", c);
			session.setAttribute(Constants.PAGE, Constants.PAGE_RESERVATION);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "登录成功");
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, "账号或密码错误");
		}

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody Message modify(CustomerDetails c, int customerId) {
		try {
			Customer c1 = customerService.modify(c, customerId);
			session.setAttribute("c", c1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "内部错误");
		}
		session.setAttribute(Constants.PAGE, Constants.PAGE_DETAILS);
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "修改成功");
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public @ResponseBody Message reg(String tel, String password, Model model) {
		if (customerService.checkTel(tel)) {
			return new Message(Constants.MESSAGE_ERR_CODE, "手机号已使用");
		}
		CustomerDetails details = new CustomerDetails(tel, Constants.DEFAULT_AVATAR,tel);
		Customer c = new Customer(tel, password);
		c.setConsumptionTimes(0);
		c.setConsumptionCount(0.00F);
		c.setDetails(details);
		c.setRegTime(new Date().getTime());
		c.setLevel(0);
		
		if (customerService.register(c, details) == true) {
			session.setAttribute("c", c);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "注册成功");
		} 
		
		return new Message(Constants.MESSAGE_ERR_CODE, "注册失败");
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
