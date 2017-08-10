package com.xfhotel.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Site;
import com.xfhotel.hotel.service.SiteService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("site")
public class SiteController {
	@Autowired
	SiteService siteService;

	@RequestMapping(value = "/addSite",method = RequestMethod.POST)
	public @ResponseBody Message addSite(String place, double price ,int classify){
		try {
			Site site = new Site();
			site.setClassify(classify);
			site.setPlace(place);
			site.setPrice(price);
			siteService.add(site);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");	
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");	
	}
	
	@RequestMapping(value = "/getSite",method = RequestMethod.POST)
	public @ResponseBody List<Site> getSite(){
		return siteService.list();
	}
	@RequestMapping(value = "/deleteSite",method = RequestMethod.POST)
	public @ResponseBody Message deleteSite(Long id ){
		try {
			Site site = siteService.findById(id);
			siteService.delete(site);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "删除失败");	
		}
	return new Message(Constants.MESSAGE_SUCCESS_CODE, "删除成功");	
	}
	 
	@RequestMapping(value = "/getSiteyForm",method = RequestMethod.POST)
	public @ResponseBody List<Site> getSiteyForm(int classify){
		return siteService.list1(classify);	
	}
	
}
