package com.xfhotel.hotel.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.LockDAOImpl;
import com.xfhotel.hotel.entity.Lock;
import com.xfhotel.hotel.service.LockService;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.lock.DES;
import com.xfhotel.hotel.support.lock.LockOperater;

import net.sf.json.JSONObject;

@Service
public class LockServiceImpl implements LockService {
	@Autowired
	LockDAOImpl lockDAOImpl;

	@Override
	public Message changePassword(String phone, String lock_no, String password) {
		// TODO Auto-generated method stub
		Object[] values = new Object[2];
		values[0] = phone;
		values[1] = lock_no;

		Lock lock = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);
		try {
			JSONObject result = LockOperater.getInstance().pwdUpdate("", "", "", lock_no, "",
					"" + lock.getValid_time_start(), "" + lock.getValid_time_end(), "", "");
			if (result.getString("rlt_code").equals("HH0000")) {
				result = result.getJSONObject("data");
				lock.setBusiness_id(result.getString("business_id"));
				lock.setPwd_no(result.getInt("pwd_no"));
				lock.setPwd_text(DES.decrypt(result.getString("pwd_text")));
				lock.setAvailiable(0);
				lockDAOImpl.update(lock);
				return new Message(Constants.MESSAGE_SUCCESS_CODE, "成功");
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		return new Message(Constants.MESSAGE_ERR_CODE, "失败");
	}
	
	@Transactional
	@Override
	public Message addPassword(String phone, String lock_no, String time_start, String time_end) {
		try {
			String[] values = { phone, lock_no };
//			Lock lock_check = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);
//			if (lock_check != null) {
//				return new Message(Constants.MESSAGE_ERR_CODE, "已有密码");
//			}
			Calendar start = Calendar.getInstance();
			start.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(time_start));
			Calendar end = Calendar.getInstance();
			end.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(time_end));
			Lock lock = new Lock();
			lock.setPwd_user_mobile(phone);
			lock.setLock_no(lock_no);
			lock.setValid_time_end(end.getTimeInMillis());
			lock.setValid_time_start(start.getTimeInMillis());
			JSONObject result = LockOperater.getInstance().pwdAdd(lock.getLock_no(), "", lock.getValid_time_start(),
					lock.getValid_time_end(), "", lock.getPwd_user_mobile(), "", lock.getDescription(), "");
			if (result.getString("rlt_code").equals(LockOperater.LOCK_MSG_SUCCESS)) {
				result = result.getJSONObject("data");
				lock.setBusiness_id(result.getString("business_id"));
				lock.setPwd_no(result.getInt("pwd_no"));
				lock.setPwd_text(DES.decrypt(result.getString("pwd_text")));
				lock.setAvailiable(0);
				lockDAOImpl.save(lock);
				return new Message(Constants.MESSAGE_SUCCESS_CODE, "添加锁密码成功");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "设置锁密码失败");
	}

	@Transactional
	@Override
	public String viewPassword(String phone, String lock_no) {
		Object[] values = new Object[2];
		values[0] = phone;
		values[1] = lock_no;
		List<Lock> lock = lockDAOImpl.getListByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);
		String getPwd_text=null;
		for(Lock lock1 : lock){
			Date date = new Date(System.currentTimeMillis());
		Date time1 = new Date(lock1.getValid_time_end());
		Date time2 = new Date(lock1.getValid_time_start());
		if(date.before(time1)&&date.after(time2)){
			getPwd_text =lock1.getPwd_text();
			}
		}
		return getPwd_text;
//		return lock.getPwd_text();
	}

	@Transactional
	@Override
	public void deletePassword(String phone, String lock_no) {
		Object[] values = new Object[2];
		values[0] = phone;
		values[1] = lock_no;
		Lock lock = lockDAOImpl.getByHQL("from Lock l where l.pwd_user_mobile = ? and l.lock_no = ?", values);
		lockDAOImpl.delete(lock);

	}

	@Transactional
	@Override
	public int verify(String business_id, String lock_no, int pwd_no, String pwd_user_mobile) {
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
