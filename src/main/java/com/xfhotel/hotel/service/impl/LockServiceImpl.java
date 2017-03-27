package com.xfhotel.hotel.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.LockDAOImpl;
import com.xfhotel.hotel.entity.Lock;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.supprot.lock.DES;
import com.xfhotel.hotel.supprot.lock.LockOperater;

@Service
public class LockServiceImpl implements LockService {
	@Autowired
	LockDAOImpl lockDAOImpl;

	@Override
	@Transactional
	public void changePassword(String phone, String lock_no, String password) {
		// TODO Auto-generated method stub
		Object[] values = new Object[2];
		values[0] = phone;
		values[1] = lock_no;
		Lock lock = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);

		JSONObject param = new JSONObject();
		param.put("pwd_text", password);
		JSONObject result = new JSONObject(LockOperater.addPassword(param));
		try {
			if (result.getString("rlt_code").equals("HH0000")) {
				result = result.getJSONObject("data");
				lock.setBusiness_id(result.getString("business_id"));
				lock.setPwd_no(result.getInt("pwd_no"));
				lock.setPwd_text(password);
				lock.setAvailiable(0);
				lockDAOImpl.update(lock);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
	}

	
	
	@Override
	@Transactional
	public String addPassword(Lock lock) throws  Exception {
		// TODO Auto-generated method stub
		Object[] values = new Object[2];
		values[0] = lock.getPwd_user_mobile();
		values[1] = lock.getLock_no();
		Lock lock_check = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?",values);
		if(lock_check!=null){
			return "";
		}else{
			JSONObject param = new JSONObject();
			param.put("lock_no", lock.getLock_no());
			param.put("valid_time_start", lock.getValid_time_start());
			param.put("valid_time_end", lock.getValid_time_end());
			param.put("pwd_user_mobile", lock.getPwd_user_mobile());
			param.put("description", lock.getDescription());
			JSONObject result = new JSONObject(LockOperater.addPassword(param));
			if(result.getString("rlt_code").equals("HH0000")){
				result = result.getJSONObject("data");
				lock.setBusiness_id(result.getString("business_id"));
				lock.setPwd_no(result.getInt("pwd_no"));
				lock.setPwd_text(DES.decrypt(result.getString("pwd_text")));
				lock.setAvailiable(0);
				lockDAOImpl.save(lock);
			}
			return "";
		}
	}

	@Override
	@Transactional
	public String viewPassword(String phone, String lock_no) {
		// TODO Auto-generated method stub
		Object[] values = new Object[2];
		values[0] = phone;
		values[1] = lock_no;
		Lock lock = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);

		return lock.getPwd_text();
	}

	@Override
	@Transactional
	public void deletePassword(String phone, String lock_no) {
		// TODO Auto-generated method stub
		Object[] values = new Object[2];
		values[0] = phone;
		values[1] = lock_no;
		Lock lock = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);

		lockDAOImpl.delete(lock);
	}

	@Override
	@Transactional
	public int verify(String business_id, String lock_no, int pwd_no, String pwd_user_mobile) {
		// TODO Auto-generated method stub
		Object[] values = new Object[3];
		values[0] = pwd_user_mobile;
		values[1] = lock_no;
		values[2] = pwd_no;
		Lock lock = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ? and l.pwd_no = ?",
				values);
		if (lock == null || lock.getAvailiable() == 1)
			return 0;
		else {
			lock.setAvailiable(1);
			lockDAOImpl.update(lock);
			return 1;
		}
	}

}
