package com.xfhotel.hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.Lock;
import com.xfhotel.hotel.service.LockService;

@Controller
public class LockController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	LockService lockService;
	
	@RequestMapping(value = "/addpassword", method = RequestMethod.POST)
	public @ResponseBody String addPassword(String phone, String lock_no, String time_start, String time_end) {
		Calendar start = Calendar.getInstance();
		try {
			start.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(time_start));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar end = Calendar.getInstance();
		try {
			end.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(time_end));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lock lock = new Lock();
		lock.setPwd_user_mobile(phone);
		lock.setLock_no(lock_no);
		lock.setValid_time_end(end.getTimeInMillis());
		lock.setValid_time_start(start.getTimeInMillis());
		String ostr="";
		try {
			ostr = lockService.addPassword(lock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			e.printStackTrace();
			return e.toString();
		}
		return ostr;
	}
	
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
