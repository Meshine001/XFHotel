package com.xfhotel.hotel.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.UserService;

import antlr.collections.List;

@Controller
@RequestMapping("/admin/apartment")
public class ApartmentController {
	
	@Autowired
	ApartmentService apartmentService;
	
	@RequestMapping(value="/init")
	public String addapartment(){
		return "/admin/addapartment";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody String add(){
		Apartment apartment = new Apartment(0,0,null,null,"交大","11"+";"+"19","南","2"+";"+"1"+";"+"1"+";"+"1");
		Room r1 = new Room(apartment, 10.1, "2","南","E室内", 2,900);
		Set rl = new HashSet();
		rl.add(r1);
		//apartment.setRooms(rl);
		apartmentService.Add(apartment);
		return "";
	}
}
