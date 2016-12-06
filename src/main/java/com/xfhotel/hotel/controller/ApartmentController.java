package com.xfhotel.hotel.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.UserService;


@Controller
@RequestMapping("/admin/apartment")
public class ApartmentController {
	
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	FacilityService facilityService;
	
	@RequestMapping(value="/init")
	public String addapartment(HttpSession session){
		List lf =  facilityService.listFacilities();
		session.setAttribute("lf", lf);
		return "/admin/addapartment";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody String add(HttpServletRequest request){
		Apartment apartment = new Apartment();
		apartment.setAddress(request.getParameter("address") +"@"+ request.getParameter("community") +"@"+ request.getParameter("num_building"));
		apartment.setFloor(request.getParameter("floor") +"@"+ request.getParameter("totalfloor"));
		apartment.setDirection(request.getParameter("direction"));
		apartment.setSquare(Double.valueOf(request.getParameter("square")));
		apartment.setCapacity(request.getParameter("capacity"));
		apartment.setLayout(request.getParameter("bedroom") +"@"+ request.getParameter("livingroom") +"@"+ request.getParameter("bathroom") +"@"+ request.getParameter("balcony"));
		apartment.setDescription(request.getParameter("description"));
		List lf =  facilityService.listFacilities();
		Iterator it = lf.iterator();
		Set fs = new HashSet();
		while(it.hasNext()){
			Facility f = (Facility) it.next();
			if(request.getParameter(f.getDescription())=="1"){
				fs.add(f);
			}
		}
		apartment.setType(request.getParameter("type1") +"@"+ request.getParameter("type2"));
		apartment.setFacilities(fs);
		apartment.setRooms(null);
		apartment.setPrices(null);
		apartment.setFeatures(null);
		apartmentService.Add(apartment);
		
		return "";
	}
}
