package com.xfhotel.hotel.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "/customer/home";
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody User jsonTest() {
		User u = new User();
		u.setId(1);
		u.setUsername("user");
		u.setPassword("123456");
		u.setAuthority(1);
		return u;
	}

	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String regPage() {
		return "/customer/register";
	}

	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "/customer/login";
	}
}
