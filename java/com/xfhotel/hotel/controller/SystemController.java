package com.xfhotel.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Banner;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/admin/system")
public class SystemController {
	
	
	@RequestMapping(value = "/banner/updatelist", method = RequestMethod.POST)
	public @ResponseBody Message updateBannerList(){
		
		return new Message(Constants.MESSAGE_ERR_CODE, "更新失败");
	}
	
	@RequestMapping(value = "/banner/add", method = RequestMethod.POST)
	public @ResponseBody Message addBanner(String linkUrl,String title,String pic,boolean showHome){
		try {
//			Banner banner = new Banner();
//			banner.setLinkUrl(linkUrl);
//			banner.setTitle(title);
//			banner.setPic(pic);
//			banner.setShowHome(showHome);
//			System.out.println(banner);
//			bannerService.add(banner);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "添加失败");
		}
	
	}
}
