package com.xfhotel.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/kfc")
public class kfcController {
	@RequestMapping(value = "/kfclist", method = RequestMethod.GET)
	public String homePage() {
		return "kfc/kfclist";
	}
}
