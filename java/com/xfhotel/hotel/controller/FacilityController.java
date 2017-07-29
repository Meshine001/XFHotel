package com.xfhotel.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.support.Message;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("facility")
public class FacilityController {
	@Autowired
	FacilityService facilityservice;
	
	@RequestMapping(value = "/addFacility",method = RequestMethod.POST)
	public @ResponseBody Message addFacility(String name, double price ,int classify){
		try {
			Facility facility = new Facility();
			facility.setName(name);
			facility.setPrice(price);
			facility.setClassify(classify);
			facilityservice.add(facility);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");	
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");	
	}
	
	@RequestMapping(value = "/getFacility",method = RequestMethod.POST)
	public @ResponseBody List<Facility> getFacility(){
		return facilityservice.list();
	}
	@RequestMapping(value = "/deleteFacility",method = RequestMethod.POST)
	public @ResponseBody Message deleteFacility(Long id ){
		try {
			Facility facility = facilityservice.findById(id);
			facilityservice.delete(facility);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");	
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");	
	}
	 
	@RequestMapping(value = "/getFacilityForm",method = RequestMethod.POST)
	public @ResponseBody List<Facility> getFacilityForm(int classify){
		return facilityservice.list1(classify);	
	}
}
