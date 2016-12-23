package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.service.impl.ApartmentServiceImpl;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.StringSplitUtil;

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
	FileService fileService;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/features/add", method = RequestMethod.POST)
	public @ResponseBody Message addFeatures(String description) {
		Feature f = featureService.add(description);
		if (f == null) {
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		} else {
			return new Message(Constants.MESSAGE_SUCCESS_CODE, f);
		}

	}

	@RequestMapping(value = "/features/delete", method = RequestMethod.POST)
	public @ResponseBody Message deleteFeatures(Feature f) {
		try {
			featureService.delete(f);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
	}

	@RequestMapping(value = "/features/update", method = RequestMethod.POST)
	public @ResponseBody Message updateFeatures(Feature f) {
		try {
			featureService.update(f);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "修改失败");
	}

	@RequestMapping(value = "/features", method = RequestMethod.GET)
	public @ResponseBody Message features() {
		List featrues = featureService.listFeatures();
		if (!featrues.isEmpty()) {
			return new Message(Constants.MESSAGE_SUCCESS_CODE, featrues);
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "暂时没有特色信息");
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request) {
		List l_facility = facilityService.listFacilities();
		session.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		session.setAttribute("l_feature", l_feature);
		String[] l_apartmenttype = { "酒店型", "短租型" };
		session.setAttribute("l_apartmenttype", l_apartmenttype);
		return "/admin/apartment/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, String address, String location, String lng, String lat,
			String community, String num_building, String floor, String totalfloor, String direction, String square,
			String capacity, String bedroom, String livingroom, String bathroom, String balcony, String description,
			String[] facility, String[] feature, String apartmenttype, String type, String num_room, String[] prices,
			@RequestParam(value = "file", required = false) MultipartFile[] file, RedirectAttributes attr) {

		Apartment apartment = new Apartment();
		apartment.setAddress(address + "@" + community + "@" + num_building + "@" + location);
		apartment.setLatitude(Double.valueOf(lat));
		apartment.setLongitude(Double.valueOf(lng));
		apartment.setFloor(floor + "@" + totalfloor);
		apartment.setDirection(direction);
		apartment.setSquare(Double.valueOf(square));
		apartment.setCapacity(capacity);
		apartment.setLayout(bedroom + "@" + livingroom + "@" + bathroom + "@" + balcony);
		apartment.setDescription(description);
		StringBuffer pics = new StringBuffer();
		// 户型图1@客厅餐厅2@卫生间2,厨房2@小区实景2
		apartment.setPic1(fileService.saveFile(file[0], request.getSession().getServletContext().getRealPath("/")));

		String k1 = fileService.saveFile(file[1], request.getSession().getServletContext().getRealPath("/"));
		String k2 = fileService.saveFile(file[2], request.getSession().getServletContext().getRealPath("/"));
		String k3 = fileService.saveFile(file[3], request.getSession().getServletContext().getRealPath("/"));
		String k4 = fileService.saveFile(file[4], request.getSession().getServletContext().getRealPath("/"));
		pics.append(k1).append("@").append(k2).append("@").append(k3).append("@").append(k4);
		apartment.setPic2(pics.toString());// 客厅餐厅

		pics = new StringBuffer();
		k1 = fileService.saveFile(file[5], request.getSession().getServletContext().getRealPath("/"));
		k2 = fileService.saveFile(file[6], request.getSession().getServletContext().getRealPath("/"));
		k3 = fileService.saveFile(file[7], request.getSession().getServletContext().getRealPath("/"));
		k4 = fileService.saveFile(file[8], request.getSession().getServletContext().getRealPath("/"));
		pics.append(k1).append("@").append(k2).append("@").append(k3).append("@").append(k4);
		apartment.setPic3(pics.toString());// 卫生间厨房

		pics = new StringBuffer();
		k1 = fileService.saveFile(file[5], request.getSession().getServletContext().getRealPath("/"));
		k2 = fileService.saveFile(file[6], request.getSession().getServletContext().getRealPath("/"));
		pics.append(k1).append("@").append(k2);
		apartment.setPic4(pics.toString());// 小区实景

		for (int i = 0; i < file.length; i++) {
			String str = "";
			if (file[i].equals("")) {
				str = "无";
			} else {
				str = fileService.saveFile(file[i], request.getSession().getServletContext().getRealPath("/"));
			}

			if (i != file.length - 1) {
				pics.append(str).append("@");
			} else {
				pics.append(str);
			}

		}

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

		apartment.setApartmentType(apartmenttype);
		if (apartmenttype.equals("酒店型")) {
			apartment.setType(apartmenttype);
		} else {
			apartment.setType(type);
		}

		apartment.setRooms(null);

		apartment.setPrices(StringSplitUtil.buildStrGroup(prices));

		apartmentService.add(apartment);

		for (int i = 0; i < Integer.valueOf(num_room); i++) {
			Room room = new Room();
			room.setApartment(apartment);
			room.setCapacity("1");
			//roomName @ roomType @ leasyType
			room.setDescription("房间" + String.valueOf(i + 1) + "@" + "-1" + "@" + type);
			room.setSquare(0);
			room.setDirection("-1");
			room.setPics("default.jpg@default.jpg@default.jpg");
			room.setPrices("-1@-1@-1@-1");
			room.setFacilities(null);
			roomService.add(room);
			room.setApartment(apartment);
		}

		attr.addAttribute("apartmentid", apartment.getId());
		return "redirect:/admin/apartment/edit";

	}

	//
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	// public String update(HttpServletRequest request, String apartmentid,
	// String address, String community,
	// String num_building, String floor, String totalfloor, String direction,
	// String square, String capacity,
	// String bedroom, String livingroom, String bathroom, String balcony,
	// String description, String[] facility,
	// String[] feature, String apartmenttype, String type, String num_room,
	// String[] leasetypeid,
	// String[] leasetype, RedirectAttributes attr) {
	// Apartment apartment =
	// apartmentService.findById(Long.valueOf(apartmentid));
	// apartment.setAddress(address + "@" + community + "@" + num_building);
	// apartment.setFloor(floor + "@" + totalfloor);
	// apartment.setDirection(direction);
	// apartment.setSquare(Double.valueOf(square));
	// apartment.setCapacity(capacity);
	// apartment.setLayout(bedroom + "@" + livingroom + "@" + bathroom + "@" +
	// balcony);
	// apartment.setDescription(description);
	// Set s_facility = new HashSet();
	// if (facility != null) {
	// for (int i = 0; i < facility.length; i++) {
	// Long id = Long.valueOf(facility[i]);
	// s_facility.add(facilityService.findById(id));
	// }
	// }
	// apartment.setFacilities(s_facility);
	// Set s_feature = new HashSet();
	// if (feature != null) {
	// for (int i = 0; i < feature.length; i++) {
	// Long id = Long.valueOf(feature[i]);
	// s_feature.add(featureService.findById(id));
	// }
	// }
	// apartment.setFeatures(s_feature);
	// apartment.setApartmentType(apartmentTypeService.findById(Long.valueOf(apartmenttype)));
	// apartment.setType(type);
	// if (type.equals("1") || type.equals("3")) {
	// Set ps = new HashSet();
	// for (int i = 0; i < leasetype.length; i++) {
	// Price p = new Price(Long.valueOf(leasetype[i]),
	// leaseTypeService.findById(Long.valueOf(leasetypeid[i])),
	// apartment, null);
	// priceService.add(p);
	// ps.add(p);
	// }
	// apartment.setPrices(ps);
	// } else
	// apartment.setPrices(null);
	// apartmentService.add(apartment);
	// attr.addAttribute("apartmentid", apartment.getId());
	// return "redirect:/admin/apartment/edit";
	// }
	//
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String apartmentid) {
		session.setAttribute("apartmentid", apartmentid);
		List l_facility = facilityService.listFacilities();
		session.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		session.setAttribute("l_feature", l_feature);
		return "/admin/apartment/update";
	}

	@RequestMapping(value = "/getapartment", method = RequestMethod.POST)
	public @ResponseBody Map getApartment(String apartmentid) {
		Map map = apartmentService.getApartmentInfo(Long.valueOf(apartmentid));
		return map;
	}

	@RequestMapping(value = "/editroom", method = RequestMethod.POST)
	public String editroom(String roomid, String apartmentid) {
		session.setAttribute("roomid", roomid);
		session.setAttribute("apartmentid", apartmentid);
		List l_facility = facilityService.listFacilities();
		session.setAttribute("l_facility", l_facility);
		List l_feature = featureService.listFeatures();
		session.setAttribute("l_feature", l_feature);
		session.setAttribute("room", roomService.getRoomInfo(Long.valueOf(roomid)));
		return "/admin/apartment/editroom";
	}

	@RequestMapping(value = "/room/{roomid}", method = RequestMethod.POST)
	public @ResponseBody Map getRoom(@PathVariable("roomid") String roomid) {
		Map map = roomService.getRoomInfo(Long.valueOf(roomid));
		return map;
	}

	@RequestMapping(value = "/room/update", method = RequestMethod.POST)
	public String updateroom(RedirectAttributes attr, HttpServletRequest request, String id, String description,
			String type,String ltype, String square, String direction, String[] facility, String[] prices,
			@RequestParam(value = "file", required = false) MultipartFile[] file) {
		String apartmentid = (String) session.getAttribute("apartmentid");
		Room room = roomService.findById(Long.valueOf(id));
		// 添加照片
		StringBuffer pics = new StringBuffer();
		for (int i = 0; i < file.length; i++) {
			if (file[i] == null)
				continue;
			String p = fileService.saveFile(file[i], request.getSession().getServletContext().getRealPath("/"));
			System.out.println(p.toString());
			if (i != file.length - 1) {
				pics.append(p).append("@");
			} else {
				pics.append(p);
			}
		}
		room.setPics(pics.toString());

		List lf = facilityService.listFacilities();
		room.setDescription(description + "@" + type + "@" + ltype);
		room.setSquare(Double.valueOf(square));
		room.setDirection(direction);
		
		List l_facility = facilityService.listFacilities();
		Set s_facility = new HashSet();
		if (facility != null) {
			for (int i = 0; i < facility.length; i++) {
				Long fid = Long.valueOf(facility[i]);
				s_facility.add(facilityService.findById(fid));
			}
		}
		room.setFacilities(s_facility);
		
		room.setPrices(StringSplitUtil.buildStrGroup(prices));
		
		roomService.update(room);
		
		attr.addAttribute("apartmentid", apartmentid);
		
		return "redirect:/admin/apartment/edit";
	}
}
