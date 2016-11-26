package com.xfhotel.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.UserService;

@Controller
@RequestMapping("/admin/user/")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody User add(User u){
		userService.addUser(u);
		return u;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<User> all(){
		List<User> list = userService.listUsers();
		return list;
	}
	
}
