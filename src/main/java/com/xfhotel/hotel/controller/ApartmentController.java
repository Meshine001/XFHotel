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
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.service.UserService;


@Controller
@RequestMapping("/admin/apartment")
public class ApartmentController {
	
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	FacilityService facilityService;
	@Autowired
	RoomService roomService;
	@Autowired
	FeatureService featureService;
	
	@RequestMapping(value="/init")
	public String addapartment(HttpSession session){
		List lf =  facilityService.listFacilities();
		session.setAttribute("lf", lf);
		List lfea = featureService.listFeatures();
		session.setAttribute("lfea", lfea);
		return "/admin/addapartment";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request){
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
			try{
				if(request.getParameter(f.getDescription()).equals("1")){
					fs.add(f);
				}
			}catch(Exception e){
				continue;
			}
		}
		apartment.setFacilities(fs);
		List lfea =  featureService.listFeatures();
		Iterator itf = lfea.iterator();
		Set feas = new HashSet();
		while(itf.hasNext()){
			Feature f = (Feature) itf.next();
			try{
				if(request.getParameter(f.getDescription()).equals("1")){
					feas.add(f);
				}
			}catch(Exception e){
				continue;
			}
		}
		apartment.setFeatures(feas);
		apartment.setType(request.getParameter("type1") +"@"+ request.getParameter("type2"));
		apartment.setRooms(null);
		apartment.setPrices(null);
		apartmentService.Add(apartment);
		if( request.getParameter("num_room").equals("0") )
			return "/admin/addcomplete";
		else{
			request.getSession().setAttribute("num_room", request.getParameter("num_room"));
			request.getSession().setAttribute("lf", lf);
			request.getSession().setAttribute("apartment_id", apartment.getId());
			return "/admin/addroom";
		}
	}
	
	@RequestMapping(value="/addroom",method=RequestMethod.POST)
	public String addroom(HttpServletRequest request){
		Apartment apartment = apartmentService.findById(Long.valueOf(request.getParameter("apartment_id")));
		List lf =  facilityService.listFacilities();
		int num_room = Integer.valueOf(request.getParameter("num_room"));
		for(Integer i=1;i<=num_room;i++){
			Room room = new Room();
			room.setApartment(apartment);
			room.setCapacity("0");
			room.setDescription(request.getParameter("id_"+i.toString()) +"@"+ request.getParameter("type_"+i.toString()));
			room.setSquare(Double.valueOf(request.getParameter("square_"+i.toString())));
			room.setDirection(request.getParameter("direction_"+i.toString()));
			room.setPrices(null);
			Iterator it = lf.iterator();
			Set fs = new HashSet();
			while(it.hasNext()){
				Facility f = (Facility) it.next();
				try{
					if(request.getParameter(f.getDescription()+"_"+i.toString()).equals("1")){
						fs.add(f);
					}
				}catch(Exception e){
					continue;
				}
			}
			room.setFacilities(fs);
			roomService.add(room);
		}
		return null;
	}
}
