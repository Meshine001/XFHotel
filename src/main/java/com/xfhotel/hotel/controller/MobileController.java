package com.xfhotel.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mobile")
public class MobileController {
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public @ResponseBody Map home(){
		Map< String, Object> info = new HashMap<String, Object>();
		info.put("banner", "sdsadsad.jpg");
		return info;
		
	}
	
	
	
	@RequestMapping(value = "/croTest",method = RequestMethod.POST)
	public @ResponseBody Map croTest(String data){
		Map< String, Object> info = new HashMap<String, Object>();
		System.out.println(data);
		info.put("banner", "sdsadsad.jpg");
		return info;
		
	}
}
