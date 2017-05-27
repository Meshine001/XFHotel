package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.CleanDAOImpl;
import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.SystemConfService;
import com.xfhotel.hotel.support.sms.SendTemplateSMS;

@Service
public class CleanServiceImpl implements CleanService {
	@Autowired
	CleanDAOImpl CleanDAO;
	
	@Autowired
	OrderDAOImpl orderDAO;
	
	@Autowired
	SystemConfService systemConfiService;
	
	@Transactional
	@Override
	public Clean findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void add(Clean t) {
		// TODO Auto-generated method stub
		
//		Order o = orderDAO.get(t.getOederId());
		//TODO
		//发短信给管理员
		//【青舍都市】您有新订单需要确认，请及时处理。{1}
//		String[] p = {o.getDescription()};
//		SendTemplateSMS.sendSMS(Constants.SMS_INFORM_COMFIRM_CLEAN_ORDER, systemConfiService.getConfig().getSms(), p);
////		
		CleanDAO.save(t);
	
		
		
	}


	@Transactional
	@Override
	public void delete(Clean t) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void update(Clean t) {
		// TODO Auto-generated method stub
		CleanDAO.update(t);
		
	}

	@Transactional
	@Override
	public List<Clean> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Clean get(Long id) {
		return CleanDAO.get(id);
	}

}
