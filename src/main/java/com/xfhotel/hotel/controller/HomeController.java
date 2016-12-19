package com.xfhotel.hotel.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.support.Area;
import com.xfhotel.hotel.support.LayoutType;
import com.xfhotel.hotel.support.LeasePrice;
import com.xfhotel.hotel.support.RoomStatus;
import com.xfhotel.hotel.support.Subway;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	FeatureService featrueService;
	@Autowired
	HttpSession session;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "/customer/home";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		session.setAttribute("areas", Area.getAreas());
		session.setAttribute("subways", Subway.getSubways());
		session.setAttribute("leasePrices", LeasePrice.getPrices());
		session.setAttribute("layoutTypes", LayoutType.getLayouts());
		session.setAttribute("features", featrueService.listFeatures());
		session.setAttribute("roomStatus", RoomStatus.getStatusArray());
		return "/customer/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String search() {
		
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
