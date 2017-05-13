package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Lock;

public interface LockService {
	public void changePassword(String phone, String lock_no,String password);
	public String addPassword(Lock lock) throws Exception;
	public String addPassword(String phone, String lock_no, String time_start, String time_end) throws Exception;
	public String viewPassword(String phone, String lock_no);
	public void deletePassword(String phone, String lock_no);
	public int verify(String business_id, String lock_no, int pwd_no, String pwd_user_mobile);
}
