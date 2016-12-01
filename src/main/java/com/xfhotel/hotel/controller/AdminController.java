package com.xfhotel.hotel.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("")
	public String admin(HttpSession session){
		User u = (User) session.getAttribute("admin");
		if(null == u){
			return "admin/login";
		}
		return "admin/dashboard";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username,String password,HttpSession session){
		User u = userService.getUser(username, password);
		if(null == u) return "";
		session.setAttribute("admin", u);
		return "admin/dashboard";
	}
	
}
