package com.xfhotel.hotel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用于一些嵌入的小功能，一般为公共的功能
 * 对应的视图为views/utils/*
 * css和js文件请放入dist/utls/*中
 * @author Ming
 *
 */
@Controller
@RequestMapping("utils")
public class UtilsController {
	@Autowired
	HttpSession seesion;
	
	@RequestMapping(value = "/roomGps", method = RequestMethod.GET)
	public String roomGps(Double lng,Double lat) {
		seesion.setAttribute("lng", lng);
		seesion.setAttribute("lat", lat);
		return "utils/gps";
	}
}
