package com.xfhotel.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfhotel.hotel.service.LockService;

@Controller
public class LockController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	LockService lockService;
	
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(String phone, String lock_no, String password) {
		lockService.changePassword(phone, lock_no, password);
		return "";
	}

	@RequestMapping( value = "/viewpassword", method = RequestMethod.POST)
	public String viewPassword(HttpServletRequest request, String phone, String lock_no, String password) {
		request.setAttribute("lock_password", lockService.viewPassword(phone, lock_no));
		return "";
	}
	
	@RequestMapping( value = "/deletepassword", method = RequestMethod.POST)
	public String deletePassword(HttpServletRequest request, String phone, String lock_no, String password) {
		lockService.deletePassword(phone, lock_no);
		return "";
	}
}
