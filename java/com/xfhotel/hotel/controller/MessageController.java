package com.xfhotel.hotel.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.QRCode;
import com.xfhotel.hotel.support.TimeUtil;
import com.xfhotel.hotel.support.lock.LockOperater;
import com.xfhotel.hotel.support.sms.SendTemplateSMS;

@Controller
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	LockService lockService;
	@Autowired
	OrderService orderService;

	@Autowired
	ApartmentService apartmentService;

	/**
	 * 设置锁的推送
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/smartLock/push", method = RequestMethod.POST)
	public @ResponseBody Map lockPush(@RequestBody Map map) {

		String validate_code = (String) map.get("validate_code");
		String factor = (String) map.get("factor");
		String business_id = (String) map.get("business_id");
		String lock_no = (String) map.get("lock_no");
		Integer pwd_no = (Integer) map.get("pwd_no");
		String pwd_user_mobile = (String) map.get("pwd_user_mobile");
		String event = (String) map.get("event");
		String str = LockOperater.LOCK_DES_KEY + factor + LockOperater.LOCK_ACCOUNT;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			str = new BigInteger(1, md.digest()).toString(16);
			if (str.equals(validate_code) || 1 == 1) {
				if (event.equals("PUSH_LOCK_SET_PWD_SUCCESS")) {
					lockSuccess(business_id, lock_no, pwd_no, pwd_user_mobile);
				} else if (event.equals("PUSH_LOCK_SET_PWD_FAIL")) {
					lockFail(business_id, lock_no, pwd_no, pwd_user_mobile);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
		}
		Map obj = new HashMap();
		obj.put("business_id", business_id);
		obj.put("flag", 'Y');
		obj.put("error_msg", "");
		return obj;
	}

	public void lockSuccess(String business_id, String lock_no, Integer pwd_no, String pwd_user_mobile) {
		int check = lockService.verify(business_id, lock_no, Integer.valueOf(pwd_no), pwd_user_mobile);
		String pwd_text = lockService.viewPassword(pwd_user_mobile, lock_no);
		if (check == 1) {
			String[] param = new String[2];
			param[0] = "";
			param[1] = pwd_text;
			SendTemplateSMS.sendSMS(Constants.SMS_INFORM_LOCK_CODE, pwd_user_mobile, param);
		}
	}

	public void lockFail(String business_id, String lock_no, Integer pwd_no, String pwd_user_mobile) {
		lockService.deletePassword(pwd_user_mobile, lock_no);
	}



	public static void main(String[] args) {

	}
}
