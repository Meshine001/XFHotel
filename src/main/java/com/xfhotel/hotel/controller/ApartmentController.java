package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.LeaseType;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.ApartmentTypeService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.LeaseTypeService;
import com.xfhotel.hotel.service.RoomService;

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
	@Autowired
	ApartmentTypeService apartmentTypeService;

	@RequestMapping(value = "/init")
	public String addApartment(HttpServletRequest request) {
		List l_facility = facilityService.listFacilities();
		request.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		request.setAttribute("l_feature", l_feature);
		List l_apartmenttype = apartmentTypeService.listApartmentTypes();
		request.setAttribute("l_apartmenttype", l_apartmenttype);
		return "/admin/addapartment";
	}

	@RequestMapping(value = "/getleasetype", method = RequestMethod.POST)
	public @ResponseBody HashMap getLeaseType(HttpServletRequest request, String apartmenttypeid) {
		ApartmentType apartmentType = apartmentTypeService.findById(Long.valueOf(apartmenttypeid));
		ArrayList s_leaseType = new ArrayList();
		ArrayList s_leaseTypeId = new ArrayList();
		Iterator it = apartmentType.getLeaseTypes().iterator();
		while (it.hasNext()) {
			LeaseType lt = (LeaseType) it.next();
			s_leaseType.add(lt.getDescription());
			s_leaseTypeId.add(lt.getId());
		}
		HashMap leaseType = new HashMap();
		leaseType.put("leasetype", s_leaseType);
		leaseType.put("leasetypeid", s_leaseTypeId);
		return leaseType;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, String address, String community, String num_building, String floor,
			String totalfloor, String direction, String square, String capacity, String bedroom, String livingroom,
			String bathroom, String balcony, String description, String[] facility, String[] feature,
			String apartmenttype, String type, String num_room) {
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
		apartment.setApartmentType(apartmentTypeService.findById(Long.valueOf(apartmenttype)));
		apartment.setType(type);
		apartment.setRooms(null);
		if (type.equals("1") || type.equals("3")) {
			List l_leasetype = leaseTypeService.listLeaseTypes();
			Iterator itl = l_leasetype.iterator();
			Set ps = new HashSet();
			while (itl.hasNext()) {
				LeaseType lt = (LeaseType) itl.next();
				if (request.getParameter("leasetypes"+lt.getId())!=null && request.getParameter("leasetypes"+lt.getId()).length()>0) {
					Price p = new Price(Double.valueOf(request.getParameter("leasetypes"+lt.getId())), lt, apartment, null);
					ps.add(p);
				}
			}
			apartment.setPrices(ps);
		} else
			apartment.setPrices(null);
		apartmentService.Add(apartment);
		if (num_room.equals("0") || type.equals("1"))
			return "/admin/addcomplete";
		else {
			request.setAttribute("num_room", num_room);
			List l_facility = facilityService.listFacilities();
			request.setAttribute("l_facility", l_facility);
			request.setAttribute("apartment_id", apartment.getId());
			return "/admin/addroom";
		}
	}

	@RequestMapping(value = "/addroom", method = RequestMethod.POST)
	public String addRoom(HttpServletRequest request, String apartment_id, String num_room, String[] id, String[] type,
			String[] square, String[] direction, String facility) {
		Apartment apartment = apartmentService.findById(Long.valueOf(apartment_id));
		List lf = facilityService.listFacilities();
		int n = Integer.valueOf(num_room);
		for (int i = 0; i < n; i++) {
			Room room = new Room();
			room.setApartment(apartment);
			room.setCapacity("0");
			room.setDescription(id[i] + "@" + type[i]);
			room.setSquare(Double.valueOf(square[i]));
			room.setDirection(direction[i]);
			room.setPrices(null);
			List l_facility = facilityService.listFacilities();
			Iterator it = l_facility.iterator();
			Set fs = new HashSet();
			while (it.hasNext()) {
				Facility f = (Facility) it.next();
				try {
					if (request.getParameter(f.getId() + "_" + String.valueOf(i + 1)).equals("1")) {
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
