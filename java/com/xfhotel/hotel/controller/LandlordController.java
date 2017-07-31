package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.Landlord;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.CommentService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.FacilityOrderService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FaultService;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.service.HouseService;
import com.xfhotel.hotel.service.LandlordService;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.SystemConfService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("landlord")
public class LandlordController {
 	@Autowired
	CleanService cleanservice;
 	
	@Autowired
	LockService lockService;
	
	@Autowired
	FileService fileService;

	@Autowired
	ApartmentService apartmentService;

	@Autowired
	CustomerService customerService;
	
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	FacilityService facilityservice;
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	HttpSession session;

	@Autowired
	CommentService commentService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	BlogService blogService;

	@Autowired
	SystemConfService systemConfiService;
	
	@Autowired
	FacilityOrderService facilityOrderService;
	
	@Autowired
	HouseService houseService;
	
	@Autowired
	FaultService faultservice;

	@Autowired
	LandlordService landlordService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public @ResponseBody  Message add(Long id ,String number ,String name,Long card) {
	try {
		Customer c= customerService.getCustomer(id);
		Landlord landlord1 = new Landlord();
		landlord1.setuId(id);
		landlord1.setNumber(number);
		landlord1.setCard(card);
		landlord1.setName(name);
		landlord1.setPhone(c.getTel());
		landlord1.setRegTime(new Date().getTime());
		landlordService.add(landlord1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "注册失败");
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE, "注册成功");
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public @ResponseBody  Message register(Long id) {
		Landlord landlord = landlordService.getCustomer(id);
	try {	
		if(landlord ==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "您还为未成为房东");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "登录失败");
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE,landlord.getId());
	}
	
	@RequestMapping(value = "/Landlord", method = RequestMethod.GET)
	public @ResponseBody String Landlord() {
		List<Landlord> list = landlordService.list();
		List<Map> orders = new ArrayList<Map>();
		for (Landlord o : list) {
			orders.add(o.toMap());	
		}
		session.setAttribute("orders", orders);
		return "/admin/customer/房东";
	}
	
	@RequestMapping(value = "/allocation", method = RequestMethod.GET)
	public @ResponseBody  Message allocation(Long id ,Long roomId) {
	try {	
		if(id ==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "请提交正确id");
			} else if(roomId==null){
				return new Message(Constants.MESSAGE_ERR_CODE, "请提交正确房源id");
			}
		Apartment apartment = apartmentService.findById(roomId);
		apartment.setAffiliation(id);
		apartmentService.update(apartment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "分配失败");
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE,"分配成功");
	}
	
	@RequestMapping(value = "/particulars", method = RequestMethod.GET)
	public @ResponseBody List<Apartment> particulars(Long id) {
		return apartmentService.landlord(id);
	}
}
