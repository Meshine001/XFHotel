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
	public String home() {

		return "/customer/home";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {

		return "/customer/list";
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
	
	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String info() {
		
		return "/customer/info";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {

		return "/customer/reservation1";
	}
	
}
