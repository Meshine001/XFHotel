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
import com.xfhotel.hotel.service.LeaseTypeService;
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
	@Autowired
	LeaseTypeService leaseTypeService;

	@RequestMapping(value = "/init")
	public String addapartment(HttpServletRequest request) {
		List l_facility = facilityService.listFacilities();
		request.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		request.setAttribute("l_feature", l_feature);
		List l_leasetype = leaseTypeService.listLeaseTypes();
		request.setAttribute("l_leasetype", l_leasetype);
		return "/admin/addapartment";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, String address, String community, String num_building, String floor,
			String totalfloor, String direction, String square, String capacity, String bedroom, String livingroom,
			String bathroom, String balcony, String description, String[] facility, String[] feature, String type1,
			String type2, String num_room) {
		Apartment apartment = new Apartment();
		apartment.setAddress(address + "@" + community + "@" + num_building);
		apartment.setFloor(floor + "@" + totalfloor);
		apartment.setDirection(direction);
		apartment.setSquare(Double.valueOf(square));
		apartment.setCapacity(capacity);
		apartment.setLayout(bedroom + "@" + livingroom + "@" + bathroom + "@" + balcony);
		apartment.setDescription(description);
		Set s_facility = new HashSet();
		if (facility != null) {
			for (int i = 0; i < facility.length; i++) {
				Long id = Long.valueOf(facility[i]);
				s_facility.add(facilityService.findById(id));
			}
		}
		apartment.setFacilities(s_facility);
		Set s_feature = new HashSet();
		if (feature != null) {
			for (int i = 0; i < feature.length; i++) {
				Long id = Long.valueOf(feature[i]);
				s_feature.add(featureService.findById(id));
			}
		}
		apartment.setFeatures(s_feature);
		apartment.setType(type1 + "@" + type2);
		apartment.setRooms(null);
		apartment.setPrices(null);
		apartmentService.Add(apartment);
		if (num_room.equals("0"))
			return "/admin/addcomplete";
		else {
			request.setAttribute("num_room", request.getParameter("num_room"));
			List l_facility = facilityService.listFacilities();
			request.setAttribute("l_facility", l_facility);
			request.setAttribute("apartment_id", apartment.getId());
			return "/admin/addroom";
		}
	}

	@RequestMapping(value = "/addroom", method = RequestMethod.POST)
	public String addroom(HttpServletRequest request) {
		Apartment apartment = apartmentService.findById(Long.valueOf(request.getParameter("apartment_id")));
		List lf = facilityService.listFacilities();
		int num_room = Integer.valueOf(request.getParameter("num_room"));
		for (Integer i = 1; i <= num_room; i++) {
			Room room = new Room();
			room.setApartment(apartment);
			room.setCapacity("0");
			room.setDescription(
					request.getParameter("id_" + i.toString()) + "@" + request.getParameter("type_" + i.toString()));
			room.setSquare(Double.valueOf(request.getParameter("square_" + i.toString())));
			room.setDirection(request.getParameter("direction_" + i.toString()));
			room.setPrices(null);
			Iterator it = lf.iterator();
			Set fs = new HashSet();
			while (it.hasNext()) {
				Facility f = (Facility) it.next();
				try {
					if (request.getParameter(f.getDescription() + "_" + i.toString()).equals("1")) {
						fs.add(f);
					}
				} catch (Exception e) {
					continue;
				}
			}
			room.setFacilities(fs);
			roomService.add(room);
		}
		return null;
	}
}
