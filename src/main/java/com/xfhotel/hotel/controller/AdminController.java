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
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginPage(){
		return "/admin/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 public String login(User user,HttpSession session){
		User u = userService.getUser(user.getUsername(),user.getPassword());
		if(null == u) return "/admin/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "/admin/dashboard";
	}
	
}
