package com.xfhotel.hotel.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.xfhotel.hotel.support.sms.SendTemplateSMS;


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
	    ImageValidateCode vCode = new ImageValidateCode(120,40,6,100);  
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
	
	@RequestMapping(value="/checkVCode")  
	public @ResponseBody Message validateTelVCode(String tel,String vCode){
		try {
			Map<String, Object> sVCode = (Map<String, Object>) session.getAttribute("vCode");
			String sTel = (String) sVCode.get("tel");
			long diedLine = (Long) sVCode.get("diedLine");
			String code = (String) sVCode.get("code");
			System.out.println(sVCode);
			if(sTel.equals(tel) && code.equals(vCode)){
				if(diedLine < new Date().getTime()){
					return new Message(Constants.MESSAGE_ERR_CODE, "验证超时");
				}
				return new Message(Constants.MESSAGE_SUCCESS_CODE, "验证成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "验证失败");
	}
	
	/**
	 * 请求发送注册手机验证码
	 * @param tel
	 * @return
	 */
	@RequestMapping(value="/sendVCode")  
	public @ResponseBody Message sendTelValidateCode(String tel){
		try {
			String vCodeStr= SendTemplateSMS.generateValidateCode();
			String[] args = {vCodeStr,Constants.SMS_AVAILBEL_TIME_STR};
			//调试时可注释掉下面
			//发送短信
			SendTemplateSMS.sendSMS(Constants.SMS_TEMPLATE_REG, tel, args);
			//利用session进行验证
			Map<String, Object> vCode = new HashMap<String, Object>();
			vCode.put("tel", tel);
			vCode.put("diedLine", new Date().getTime()+Constants.SMS_AVAILBEL_TIME);
			vCode.put("code", vCodeStr);
			session.setAttribute("vCode", vCode);
			System.out.println("send vcode==>"+vCode);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "获取验证码失败");
		}
		
	}

}
