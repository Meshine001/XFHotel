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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xfhotel.hotel.common.Constants;
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
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.service.LeaseTypeService;
import com.xfhotel.hotel.service.PriceService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.service.impl.ApartmentServiceImpl;
import com.xfhotel.hotel.support.Message;

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
	public String add(HttpServletRequest request, String address, String location, String lng, String lat,
			String community, String num_building, String floor, String totalfloor, String direction, String square,
			String capacity, String bedroom, String livingroom, String bathroom, String balcony, String description,
			String[] facility, String[] feature, String apartmenttype, String type, String num_room,
			String[] leasetypeid, String[] price, @RequestParam(value="file",required=false)MultipartFile[] file, RedirectAttributes attr) {
//		System.out.println(address);
//		System.out.println(price);
//		System.out.println(request.getParameter("leasetypeid"));
		
		Apartment apartment = new Apartment();
		if(location == null){
			apartment.setAddress(address + "@" + community + "@" + num_building + "@ " );
		}else{
			apartment.setAddress(address + "@" + community + "@" + num_building + "@" +location);
		}
	
		apartment.setLatitude(Double.valueOf(lat));
		apartment.setLongitude(Double.valueOf(lng));
		apartment.setFloor(floor + "@" + totalfloor);
		apartment.setDirection(direction);
		apartment.setSquare(Double.valueOf(square));
		apartment.setCapacity(capacity);
		apartment.setLayout(bedroom + "@" + livingroom + "@" + bathroom + "@" + balcony);
		apartment.setDescription(description);

		StringBuffer pics = new StringBuffer();
		// 存储布局图
		String layoutPic = fileService.saveFile(file[0], request.getSession().getServletContext().getRealPath("/"));
		pics.append(layoutPic).append("@");
		//公寓照片
		for(int i=1;i<file.length;i++){
			if(file[i]==null)continue;
			String p = fileService.saveFile(file[i], request.getSession().getServletContext().getRealPath("/"));
			System.out.println(p.toString());
			if(i!=file.length-1){
				pics.append(p).append("@");
			}else{
				pics.append(p);
			}
		}
		
		apartment.setLayoutPic(pics.toString());
		
		
		
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
		
		//单租型
		if (type.equals("1")) {
			Set ps = new HashSet();
			for (int i = 0; i < price.length; i++) {
				Price p = new Price(Long.valueOf(price[i]), leaseTypeService.findById(Long.valueOf(leasetypeid[i])),
						null, null);
				priceService.add(p);
				ps.add(p);
			}
			apartment.setPrices(ps);
		} else{
			apartment.setPrices(null);
		}
			
		apartmentService.add(apartment);
		
		System.out.println("rooms:"+num_room);
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
	public String update(HttpServletRequest request, String apartmentid, String address, String community,
			String num_building, String floor, String totalfloor, String direction, String square, String capacity,
			String bedroom, String livingroom, String bathroom, String balcony, String description, String[] facility,
			String[] feature, String apartmenttype, String type, String num_room, String[] leasetypeid,
			String[] leasetype, RedirectAttributes attr) {
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
				Price p = new Price(Long.valueOf(leasetype[i]), leaseTypeService.findById(Long.valueOf(leasetypeid[i])),
						apartment, null);
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
		return "/admin/editroom";
	}

	@RequestMapping(value = "/getroom", method = RequestMethod.POST)
	public @ResponseBody Map getRoom(String roomid) {
		Map map = roomService.getRoomInfo(Long.valueOf(roomid));
		return map;
	}

	@RequestMapping(value = "/updateroom", method = RequestMethod.POST)
	public String updateroom(RedirectAttributes attr, HttpServletRequest request, String id, String description,
			String type, String square, String direction, String[] facility, String[] leasetype, String[] leasetypeid,@RequestParam(value="file",required=false)MultipartFile[] file) {
		String apartmentid = (String) session.getAttribute("apartmentid");
		Room room = roomService.findById(Long.valueOf(id));
		//添加照片
		StringBuffer pics = new StringBuffer();
		for(int i=0;i<file.length;i++){
			if(file[i]==null)continue;
			String p = fileService.saveFile(file[i], request.getSession().getServletContext().getRealPath("/"));
			System.out.println(p.toString());
			if(i!=file.length-1){
				pics.append(p).append("@");
			}else{
				pics.append(p);
			}
		}
		room.setPics(pics.toString());
		
		
		
		List lf = facilityService.listFacilities();
		room.setDescription(description + "@" + type + "@" + "2");
		room.setSquare(Double.valueOf(square));
		room.setDirection(direction);
		room.setPrices(null);
		List l_facility = facilityService.listFacilities();
		Set s_facility = new HashSet();
		if (facility != null) {
			for (int i = 0; i < facility.length; i++) {
				Long fid = Long.valueOf(facility[i]);
				s_facility.add(facilityService.findById(fid));
			}
		}
		room.setFacilities(s_facility);
		Set ps = new HashSet();
		for (int i = 0; i < leasetype.length; i++) {
			Price p = new Price(Long.valueOf(leasetype[i]), leaseTypeService.findById(Long.valueOf(leasetypeid[i])),
					null, room);
			priceService.add(p);
			ps.add(p);
		}
		room.setPrices(ps);
		roomService.add(room);
		attr.addAttribute("apartmentid", apartmentid);
		return "redirect:/admin/apartment/edit";
	}
}
