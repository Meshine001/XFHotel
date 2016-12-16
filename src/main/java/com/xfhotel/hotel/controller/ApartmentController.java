package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.LeaseType;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.ApartmentTypeService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.LeaseTypeService;
import com.xfhotel.hotel.service.PriceService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.service.impl.ApartmentServiceImpl;

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
	@Autowired
	PriceService priceService;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/init")
	public String addApartment(HttpServletRequest request) {
		List l_facility = facilityService.listFacilities();
		session.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		session.setAttribute("l_feature", l_feature);
		List l_apartmenttype = apartmentTypeService.listApartmentTypes();
		session.setAttribute("l_apartmenttype", l_apartmenttype);
		return "/admin/addapartment";
	}

	@RequestMapping(value = "/getleasetype", method = RequestMethod.POST)
	public @ResponseBody HashMap getLeaseType(String type, String id) {
		ApartmentType apartmentType = new ApartmentType();
		if (type.equals("0")) {
			apartmentType = apartmentTypeService.findById(Long.valueOf(id));

		} else {
			Long apartmentTypeId = roomService.getApartmentType(Long.valueOf(id));
			apartmentType = apartmentTypeService.findById(apartmentTypeId);
		}
		ArrayList s_leaseType = new ArrayList();
		ArrayList s_leaseTypeId = new ArrayList();
		Set s_LeaseType = apartmentType.getLeaseTypes();
		Iterator it = s_LeaseType.iterator();
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
			String apartmenttype, String type, String num_room, String[] leasetypeid, String[] leasetype,
			RedirectAttributes attr) {
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
			Set ps = new HashSet();
			for (int i = 0; i < leasetype.length; i++) {
				Price p = new Price(Long.valueOf(leasetype[i]), leaseTypeService.findById(Long.valueOf(leasetypeid[i])), null, null);
				priceService.add(p);
				ps.add(p);
			}
			apartment.setPrices(ps);
		} else
			apartment.setPrices(null);
		apartmentService.add(apartment);
		for (int i = 0; i < Integer.valueOf(num_room); i++) {
			Room room = new Room();
			room.setApartment(apartment);
			room.setCapacity("");
			room.setDescription("房间" + String.valueOf(i + 1) + "@" + "" + "@" + type);
			room.setSquare(0);
			room.setDirection("");
			room.setPrices(null);
			room.setFacilities(null);
			roomService.add(room);
		}
		attr.addAttribute("apartmentid", apartment.getId());
		return "redirect:/admin/apartment/edit";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, String apartmentid, String address, String community, String num_building, String floor,
			String totalfloor, String direction, String square, String capacity, String bedroom, String livingroom,
			String bathroom, String balcony, String description, String[] facility, String[] feature,
			String apartmenttype, String type, String num_room, String[] leasetypeid, String[] leasetype,
			RedirectAttributes attr) {
		Apartment apartment = apartmentService.findById(Long.valueOf(apartmentid));
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
		if (type.equals("1") || type.equals("3")) {
			Set ps = new HashSet();
			for (int i = 0; i < leasetype.length; i++) {
				Price p = new Price(Long.valueOf(leasetype[i]), leaseTypeService.findById(Long.valueOf(leasetypeid[i])), apartment, null);
				priceService.add(p);
				ps.add(p);
			}
			apartment.setPrices(ps);
		} else
			apartment.setPrices(null);
		apartmentService.add(apartment);
		attr.addAttribute("apartmentid", apartment.getId());
		return "redirect:/admin/apartment/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String apartmentid) {
		session.setAttribute("apartmentid", apartmentid);
		List l_facility = facilityService.listFacilities();
		session.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		session.setAttribute("l_feature", l_feature);
		List l_apartmenttype = apartmentTypeService.listApartmentTypes();
		session.setAttribute("l_apartmenttype", l_apartmenttype);
		return "/admin/editapartment";
	}

	@RequestMapping(value = "/getapartment", method = RequestMethod.POST)
	public @ResponseBody HashMap getApartment(String apartmentid) {
		HashMap map = apartmentService.getApartmentInfo(Long.valueOf(apartmentid));
		return map;
	}

	@RequestMapping(value = "/editroom")
	public String editroom(String roomid) {
		session.setAttribute("roomid", roomid);
		List l_facility = facilityService.listFacilities();
		session.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		session.setAttribute("l_feature", l_feature);
		return "/admin/editroom";
	}

	@RequestMapping(value = "/getroom", method = RequestMethod.POST)
	public @ResponseBody HashMap getRoom(String roomid) {
		HashMap map = roomService.getRoomInfo(Long.valueOf(roomid));
		System.out.println(map.toString());
		return map;
	}

	@RequestMapping(value = "/editroom1")
	public String editroom1(HttpServletRequest request, String apartment_id, String num_room, String[] id,
			String[] type, String[] square, String[] direction, String facility) {
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
