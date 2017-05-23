package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Lock;
import com.xfhotel.hotel.support.Message;

import net.sf.json.JSONObject;

public interface LockService {
	public Message changePassword(String phone, String lock_no,String password);
	public Message addPassword(String phone, String lock_no, String time_start, String time_end);
	public String viewPassword(String phone, String lock_no);
	public void deletePassword(String phone, String lock_no);
	public int verify(String business_id, String lock_no, int pwd_no, String pwd_user_mobile);
}
