package com.xfhotel.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.FacilityOrder;
import com.xfhotel.hotel.entity.Fault;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.TripOrder;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.FacilityOrderService;
import com.xfhotel.hotel.service.FaultService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.TripOrderService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;


@Controller
@RequestMapping("/admin/user/")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	TripOrderService tripOrderService;
	@Autowired
	FaultService faultservice;
	
	@Autowired
	OrderService orderservice;
	
 	@Autowired
	CleanService cleanservice;
	@Autowired
	ApartmentService apartmentService;
	
	@Autowired
	FacilityOrderService facilityOrderService;

	//添加管理员角色
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Message add(String username,String psd,String tel,int authority){
		try{
		User user= new User();
		List<User> user1 = userService.gtelogin(username);
		if(user1.size()>0){
			return new Message(Constants.MESSAGE_ERR_CODE, "该昵称已有人使用");
		}
		user.setAuthority(authority);
		user.setContact(tel);
		user.setUsername(username);
		user.setPassword(psd);
		user.setStatus(0);
		userService.add(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
	}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,"添加成功");
	}
	
	//修改
	@RequestMapping(value = "/amend", method = RequestMethod.POST)
	public @ResponseBody Message amend(Long id,String username,String psd,String tel,int authority){
		try{
		User user= userService.findById(id);
		user.setAuthority(authority);
		user.setContact(tel);
		user.setUsername(username);
		if(psd!=null){
			user.setPassword(psd);
		}
		userService.update(user);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "修改失败");
	}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,"修改成功");
	}
	
	//修改转态
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Message update(Long id,int status){
		try {
		User user= userService.findById(id);
		user.setStatus(status);
		userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "修改失败");
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE,"修改成功");
	}
	
	//查询所有用户
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody Message all(){
		List<User> list = userService.list();
		try{	
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
	}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,list);
	}
	
	//查询某个地方的房屋
	@RequestMapping(value = "/getRoom", method = RequestMethod.POST)
	public @ResponseBody Message getRoom(int wei){
		List<Apartment> list = apartmentService.getApartments1();
		ArrayList<Object> list1 = new ArrayList<Object>();
		try{
		for(Apartment apartment :list){
			String weizhi = apartment.getPosition().getString("xa_wei_zhi");
			int i =0;
			if(weizhi=="城东"){
				i= 0;
			} else if(weizhi=="城西"){
				i = 1;
			} else if(weizhi=="城北"){
				i = 2;
			}else if(weizhi=="城南"){
				i = 3;
			}else if(weizhi=="城中"){
				i = 4;
			}
			if(wei==5){
				list1.add(apartment);
			}else if(wei==i){
				list1.add(apartment);
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "修改失败");
	}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,list1);
}
	
	//分配房屋
	@RequestMapping(value = "/allocation", method = RequestMethod.POST)
	public @ResponseBody  Message allocation(Long id ,Long roomId[]) {
	try {	
		if(id ==null){
			return new Message(Constants.MESSAGE_ERR_CODE, "请提交正确id");
			} else if(roomId==null){
				return new Message(Constants.MESSAGE_ERR_CODE, "请提交正确房源id");
			}
		for(Long d :roomId){
			Apartment apartment = apartmentService.findById(d);
			apartment.setSteward(id);
			apartmentService.update(apartment);
		}
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "分配失败");
		}
			return new Message(Constants.MESSAGE_SUCCESS_CODE,"分配成功");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long id){
		try{	
			User list = userService.findById(id);
			userService.delete(list);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");
	}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,"删除成功");
	}
	
	//查询管理员所管理房屋
	@RequestMapping(value = "/steward", method = RequestMethod.POST)
	public @ResponseBody Message steward( Long id){
		List<Apartment> list = apartmentService.steward(id);
		try{	
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
	}
		User u =userService.findById(id);
		if(u.getAuthority()==0){
			return new Message(Constants.MESSAGE_SUCCESS_CODE,apartmentService.list());
		} else{
			return new Message(Constants.MESSAGE_SUCCESS_CODE,list);	
		}
	}
	
	//
	@RequestMapping(value = "/stewardO", method = RequestMethod.POST)
	public @ResponseBody Message stewardO( Long id){
		List<Apartment> list = apartmentService.steward(id);
		ArrayList<Object> list1 = new ArrayList<Object>();
		try{
			User u =userService.findById(id);
			if(u.getAuthority()==0){
				return new Message(Constants.MESSAGE_SUCCESS_CODE,orderservice.list(0));
			} else{
			for(Apartment apartment :list){
				List<Order> o=orderservice.getOrders(apartment.getId());
			 for(int i=0;i<o.size();i++){
				 Order o1 = o.get(i);
				 list1.add(o1);
			 }
				}
			}
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
	}
		return new Message(Constants.MESSAGE_SUCCESS_CODE,list1);	
		}
	
	@RequestMapping(value = "/stewardC", method = RequestMethod.POST)
	public @ResponseBody Message stewardC( Long id){
		List<Apartment> list = apartmentService.steward(id);
		ArrayList<Object> list1 = new ArrayList<Object>();
		try{
			User u =userService.findById(id);
			if(u.getAuthority()==0){
				return new Message(Constants.MESSAGE_SUCCESS_CODE,cleanservice.list());
			} else{
			for(Apartment apartment :list){
				List<Clean> o=cleanservice.getClean1(apartment.getId());
			 for(int i=0;i<o.size();i++){
				 Clean o1 = o.get(i);
				 list1.add(o1);
			 }
				}
			}
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
		}
			return new Message(Constants.MESSAGE_SUCCESS_CODE,list1);	
	}
	
	@RequestMapping(value = "/stewardF", method = RequestMethod.POST)
	public @ResponseBody Message stewardF( Long id){
		List<Apartment> list = apartmentService.steward(id);
		ArrayList<Object> list1 = new ArrayList<Object>();
		try{
			User u =userService.findById(id);
			if(u.getAuthority()==0){
				return new Message(Constants.MESSAGE_SUCCESS_CODE,faultservice.list());
			} else {
			for(Apartment apartment :list){
				List<Fault> o=faultservice.getFault1(apartment.getId());
			 for(int i=0;i<o.size();i++){
				 Fault o1 = o.get(i);
				 list1.add(o1);
			 }
				}
			}
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
	}
			return new Message(Constants.MESSAGE_SUCCESS_CODE,list1);	
		}
	
	@RequestMapping(value = "/stewardT", method = RequestMethod.POST)
	public @ResponseBody Message stewardT( Long id){
		List<Apartment> list = apartmentService.steward(id);
		ArrayList<Object> list1 = new ArrayList<Object>();
		try{
			User u =userService.findById(id);
			if(u.getAuthority()==0){
				return new Message(Constants.MESSAGE_SUCCESS_CODE,tripOrderService.list());
			} else {
			for(Apartment apartment :list){
				List<TripOrder> o=tripOrderService.getTripOrder1(apartment.getId());
			 for(int i=0;i<o.size();i++){
				 TripOrder o1 = o.get(i);
				 list1.add(o1);
			 }
				}
			}
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
	}
			return new Message(Constants.MESSAGE_SUCCESS_CODE,list1);	
		}
	
	@RequestMapping(value = "/stewardFO", method = RequestMethod.POST)
	public @ResponseBody Message stewardFO( Long id){
		List<Apartment> list = apartmentService.steward(id);
		ArrayList<Object> list1 = new ArrayList<Object>();
		try{
			User u =userService.findById(id);
			if(u.getAuthority()==0){
				return new Message(Constants.MESSAGE_SUCCESS_CODE,facilityOrderService.list());
			} else {
			for(Apartment apartment :list){
				List<FacilityOrder> o=facilityOrderService.getFaultOrder1(apartment.getId());
			 for(int i=0;i<o.size();i++){
				 FacilityOrder o1 = o.get(i);
				 list1.add(o1);
			 }
				}
			}
	} catch (Exception e) {
		e.printStackTrace();
		return new Message(Constants.MESSAGE_ERR_CODE, "查找失败");
	}
			return new Message(Constants.MESSAGE_SUCCESS_CODE,list1);	
		}
}