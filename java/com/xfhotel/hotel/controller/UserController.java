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
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.UserService;
import com.xfhotel.hotel.support.Message;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/user/")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	ApartmentService apartmentService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody User add(String username,String psd,String tel,int authority){
		User user= new User();
		user.setAuthority(authority);
		user.setContact(tel);
		user.setUsername(username);
		user.setPassword(psd);
		user.setStatus(0);
		userService.add(user);
		return user;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<User> all(){
		List<User> list = userService.list();
		return list;
	}
	
	@RequestMapping(value = "/getRoom", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Object> getRoom(int wei){
		List<Apartment> list = apartmentService.getApartments1();
		ArrayList<Object> list1 = new ArrayList<Object>();
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
			if(wei==i){
				list1.add(apartment);
			}
		}
		return list1;
	}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "分配失败");
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE,"分配成功");
	}
}
