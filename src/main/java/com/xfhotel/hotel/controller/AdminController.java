package com.xfhotel.hotel.controller;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FeatureService featureService;
	@Autowired
	FacilityService facilityService;
	
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
	
	@RequestMapping(value="/system",method = RequestMethod.GET)
	public String systemPage(){
		session.setAttribute("features", featureService.listFeatures());
		session.setAttribute("facilities", facilityService.listFacilities());
		return "/admin/system";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 public String login(User user){
		User u = userService.getUser(user.getUsername(),user.getPassword());
		if(null == u) return "/admin/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "/admin/dashboard";
	}
	
}
