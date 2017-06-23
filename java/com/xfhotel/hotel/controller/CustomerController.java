package com.xfhotel.hotel.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	HttpSession session;
	
	@Autowired
	OrderService orderService;
	
	

	/**
	 * 修改密码
	 * 
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
	 * 用户登出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		customerService.logout();
		return "redirect:/";
	}

	/**
	 * 用户登录
	 * 
	 * @param tel
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Message login(String tel, String password, Model model) {
		
		Customer c = customerService.login(tel, password);
		if (c != null) {
			session.setAttribute("c", c);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "登录成功");
		} else {
			return new Message(Constants.MESSAGE_ERR_CODE, "账号或密码错误");
		}

	}

	/**
	 * 修改个人信息
	 * 
	 * @param c
	 *            个人详细信息
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody Message modify(CustomerDetails c, long customerId) {
		try {
			System.out.println(c.getAvatar());
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

	/**
	 * 注册
	 * 
	 * @param tel
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public @ResponseBody Message reg(String tel, String password, Model model) {
		if (customerService.checkTel(tel)) {
			return new Message(Constants.MESSAGE_ERR_CODE, "手机号已使用");
		}
		CustomerDetails details = new CustomerDetails("", Constants.DEFAULT_AVATAR, tel);
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

	@RequestMapping(value = "/myOrder", method = RequestMethod.GET)
	public String myOrderPage() {
		Customer c = (Customer) session.getAttribute("c");
		orderService.refresh(c.getId());
		return "/customer/myOrder";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginRegPage(String forword, Model model) {
		model.addAttribute("forword", forword);
		return "/customer/login-reg";
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String detailsPage(Model model) {
		setPage(model, "我的资料", Constants.PAGE_DETAILS);
		return "/customer/details";
	}
	
	/**
	 * 获取用户详细信息
	 * @param id
	 * @return json数据
	 */
	@RequestMapping(value = "/detailsData", method = RequestMethod.GET)
	public @ResponseBody Customer getCustomerDetails(Long id){
		return customerService.getCustomer(id);
	}

	@RequestMapping(value = "/serviceCenter", method = RequestMethod.GET)
	public String serviceCenterPage() {

		return "/customer/serviceCenter";
	}
	
	@RequestMapping(value = "/preferential", method = RequestMethod.GET)
	public String preferentialPage() {

		return "/customer/preferential";
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public String feedbackPage() {

		return "/customer/feedback";
	}
	
	
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String changePasswordPage() {

		return "/customer/setting";
	}
	
	

	/**
	 * 检查手机是否被注册
	 * 
	 * @param tel
	 * @return true被注册，false未被注册
	 */
	@RequestMapping(value = "/checkTel", method = RequestMethod.GET)
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
