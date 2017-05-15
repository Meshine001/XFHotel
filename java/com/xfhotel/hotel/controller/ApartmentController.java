package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.service.impl.ApartmentServiceImpl;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.StringSplitUtil;
import com.xfhotel.hotel.support.TimeUtil;

import net.sf.json.JSONObject;

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
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
		}

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String apartmentList() {
		session.setAttribute("apartments", apartmentService.list());
		return "/admin/apartment/list";
	}

	@RequestMapping(value = "/features/delete/{id}", method = RequestMethod.POST)
	public @ResponseBody Message deleteFeatures(@PathVariable("id") Long id) {
		try {
			Feature f = featureService.findById(id);
			featureService.delete(f);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
	}

	@RequestMapping(value = "/facility/add", method = RequestMethod.POST)
	public @ResponseBody Message addFacility(String description) {
		try {
			Facility f = new Facility();
			f.setDescription(description);
			facilityService.add(f);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}

	}

	@RequestMapping(value = "/facility/delete/{id}", method = RequestMethod.POST)
	public @ResponseBody Message deleteFacility(@PathVariable("id") Long id) {
		try {
			Facility f = facilityService.findById(id);
			facilityService.delete(f);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
	}

	@RequestMapping(value = "/features", method = RequestMethod.GET)
	public @ResponseBody Message features() {
		List featrues = featureService.listFeatures();
		if (!featrues.isEmpty()) {
			return new Message(Constants.MESSAGE_SUCCESS_CODE, featrues);
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "暂时没有特色信息");
	}

	/**
	 * 跳转添加公寓页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		
		return "/admin/apartment/add";
	}


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se, String jia_ju, String wei_yu, String can_chu,
			String pei_tao, String zou_bian, String qi_ta, String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge) {

		Apartment apartment = apartmentService.add(jing_du, wei_du, bd_wei_zhi, xa_wei_zhi, jie_dao, xiao_qu, lou_hao,
				dan_yuan, lou_ceng, zong_lou_ceng, men_pai, suo_di_zhi, cao_xiang, mian_ji, shi, ting, wei, yang_tai,
				reng_shu, chuang, miao_su, te_se, jia_ju, wei_yu, can_chu, pei_tao, zou_bian, qi_ta, pic1, pic2, pic3,
				lei_xing, jia_ge);
		
		return "redirect:/admin/apartment/update/" + apartment.getId();
	};

	@RequestMapping("get")
	@ResponseBody
	public JSONObject get(Long id){
		return apartmentService.getApartmentById(id);
	}
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//	public String update(@PathVariable("id") Long id, String address, String location, String lng, String lat,
//			String community, String num_building, String num_unit, String num_door, String floor, String totalfloor,
//			String direction, Double square, String bedroom, String livingroom, String bathroom, String capacity,
//			String balcony, String descriptionAround, String descriptionPersonal, String[] facility, String[] feature,
//			String apartmenttype, String dayPrice, String pic1, String[] pic2, String[] pic3) {
//
//		
////		apartmentService.update(apartment);
//
//		return "redirect:/admin/apartment/update/" + apartment.getId();
//	}

//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//	public String updatePage(@PathVariable("id") Long id) {
//		Map<String, Object> apartment = apartmentService.getApartmentInfo(id);
//		session.setAttribute("apartment", apartment);
//		List<Facility> l_facility = facilityService.listFacilities();
//		session.setAttribute("l_facility", l_facility);
//		List<Feature> l_feature = featureService.listFeatures();
//		session.setAttribute("l_feature", l_feature);
//		String[] l_apartmenttype = { "酒店型", "休闲型" };
//		session.setAttribute("l_apartmenttype", l_apartmenttype);
//		return "/admin/apartment/update";
//	}
//
//	@RequestMapping(value = "/delete/{id}")
//	public String delete(@PathVariable("id") Long id) {
//		Apartment apartment = apartmentService.findById(id);
//		List<Room> rooms = apartment.getRooms();
//		for (Room room : rooms) {
//			roomService.delete(room);
//		}
//		apartmentService.delete(apartment);
//
//		return "forward:/admin/apartment";
//	}
//
//	@RequestMapping(value = "/getapartment", method = RequestMethod.POST)
//	public @ResponseBody Map getApartment(String apartmentid) {
//		Map map = apartmentService.getApartmentInfo(Long.valueOf(apartmentid));
//		return map;
//	}
//
//	@RequestMapping(value = "/price/set", method = RequestMethod.POST)
//	public String priceSet(Long apartmentId, String date, String price) {
//		Apartment apartment = apartmentService.findById(apartmentId);
//		Price sp = apartmentService.getSpPrice(apartment, TimeUtil.getDateLong(date));
//		if (sp != null) {
//			sp.setPrice(Double.valueOf(price));
//		} else {
//			sp = new Price(apartment, TimeUtil.getDateLong(date), Double.valueOf(price));
//		}
//		apartmentService.setSpPrice(sp);
//
//		return "redirect:/admin/apartment/price/" + apartmentId;
//	}
//
//	@RequestMapping(value = "/price/{id}", method = RequestMethod.GET)
//	public String price(@PathVariable("id") Long id) {
//		Apartment apartment = apartmentService.findById(id);
//		Long start = TimeUtil.getCurrentDateLong();
//		Long end = start + 1000 * 60 * 60 * 25 * 60;// 60天
//		List<Map> prices = apartmentService.getSpPrices(start, end, apartment);
//		session.setAttribute("apartment", apartmentService.getApartmentInfo(apartment.getId()));
//		session.setAttribute("spPrices", prices);
//		return "admin/apartment/price";
//	}
//
//	@RequestMapping(value = "/showHome/{id}", method = RequestMethod.GET)
//	public String showHome(@PathVariable("id") Long id) {
//		Map info = apartmentService.getApartmentInfo(id);
//		List<Map> rooms = (List<Map>) info.get("rooms");
//		Room room = roomService.findById((Long) rooms.get(0).get("id"));
//		room.setShowHome(room.isShowHome() ? false : true);
//		roomService.update(room);
//		return "redirect:/admin/apartment";
//	}
//
//	// @RequestMapping(value = "/editroom", method = RequestMethod.POST)
//	// public String editroom(Long roomid, String apartmentid) {
//	// session.setAttribute("roomid", roomid);
//	// session.setAttribute("apartmentid", apartmentid);
//	// List l_facility = facilityService.listFacilities();
//	// session.setAttribute("l_facility", l_facility);
//	// List l_feature = featureService.listFeatures();
//	// session.setAttribute("l_feature", l_feature);
//	// session.setAttribute("room", roomService.getRoomInfo(roomid));
//	// return "/admin/apartment/editroom";
//	// }
//
//	// @RequestMapping(value = "/room/pic/update/{roomid}", method =
//	// RequestMethod.POST)
//	// public @ResponseBody Message updateRoomPic(@PathVariable(value =
//	// "roomid") Long roomid, String[] pics) {
//	// try {
//	// roomService.updateRoomPic(roomid, pics);
//	// return new Message(Constants.MESSAGE_SUCCESS_CODE, "更新成功");
//	// } catch (Exception e) {
//	// // TODO Auto-generated catch block
//	// e.printStackTrace();
//	// }
//	// return new Message(Constants.MESSAGE_ERR_CODE, "更新房间图片失败");
//	// }
//
//	// @RequestMapping(value = "/room/{roomid}", method = RequestMethod.POST)
//	// public @ResponseBody Map getRoom(@PathVariable("roomid") String roomid) {
//	// Map map = roomService.getRoomInfo(Long.valueOf(roomid));
//	// return map;
//	// }
//
//	// @RequestMapping(value = "/room/update/{roomid}", method =
//	// RequestMethod.POST)
//	// public String updateroom(@PathVariable(value = "roomid") Long roomid,
//	// RedirectAttributes attr,
//	// HttpServletRequest request, String description, String type, String
//	// ltype, String square, String direction,
//	// String[] facility, String[] prices) {
//	// String apartmentid = (String) session.getAttribute("apartmentid");
//	// Room room = roomService.findById(roomid);
//	//
//	// List lf = facilityService.listFacilities();
//	// room.setDescription(description + "@" + type + "@" + ltype);
//	// room.setSquare(Double.valueOf(square));
//	// room.setDirection(direction);
//	//
//	// room.setFacilities(facility);
//	//
//	// room.setPrices(StringSplitUtil.buildStrGroup(prices));
//	//
//	// roomService.update(room);
//	//
//	// attr.addAttribute("apartmentid", apartmentid);
//	//
//	// return "redirect:/admin/apartment/edit";
//	// }
}
