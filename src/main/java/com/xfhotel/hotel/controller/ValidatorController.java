package com.xfhotel.hotel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.support.ImageValidateCode;
import com.xfhotel.hotel.support.Message;


/**
 * 校验控制器，用于服务器的各种校验服务
 * @author Ming
 *
 */
@Controller
@RequestMapping("/validator")
public class ValidatorController {
	
	@Autowired
	HttpSession session;

	
	/**
	 * 获取图片验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/image")  
	public String imageValidateCode(HttpServletResponse response) throws Exception{  
	    // 设置响应的类型格式为图片格式  
	    response.setContentType("image/jpeg");  
	    //禁止图像缓存。  
	    response.setHeader("Pragma", "no-cache");  
	    response.setHeader("Cache-Control", "no-cache");  
	    response.setDateHeader("Expires", 0);  
	    ImageValidateCode vCode = new ImageValidateCode(120,40,8,100);  
	    session.setAttribute("imageValidateCode", vCode.getCode());  
	    vCode.write(response.getOutputStream());  
	    return null;  
	}  
	
	@RequestMapping(value="/checkImgCode")  
	public @ResponseBody Message checkImageCode(String code){
		String sessionCode = (String) session.getAttribute("imageValidateCode");  
		System.out.println(code+","+sessionCode);
		if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {  //忽略验证码大小写  
			return new Message(Constants.MESSAGE_ERR_CODE, "验证码错误");
		}
		return new Message(Constants.MESSAGE_SUCCESS_CODE, "验证码正确");
	}

}
