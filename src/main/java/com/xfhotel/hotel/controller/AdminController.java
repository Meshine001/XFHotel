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
	public String loginPage(@RequestParam("next")Optional<String> next){
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 public String login(@RequestParam("next")Optional<String> next,HttpSession session){
		Optional<String> username = next.of("username");
		Optional<String> password = next.of("password");
		User u = userService.getUser(username.get(), password.get());
		if(null == u) return "/login";
		session.setAttribute(Constants.ADMIN_SESSION_ATTR, u);
		return "redirect:".concat(next.orElse("/"));
	}
	
}
