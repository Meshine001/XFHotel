package com.xfhotel.hotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.TripOrderDAOImpl;
import com.xfhotel.hotel.entity.TripOrder;
import com.xfhotel.hotel.service.TripOrderService;
import com.xfhotel.hotel.support.DateUtil;

@Service
public class TripOrderServiceImpl implements TripOrderService {

	@Autowired
	TripOrderDAOImpl tripOrderDAO;

	@Transactional
	@Override
	public TripOrder findById(Long id) {
		// TODO Auto-generated method stub
		return tripOrderDAO.get(id);
	}
	
	@Transactional
	@Override
	public void add(TripOrder t) {
		// TODO Auto-generated method stub
		Long start = DateUtil.getStartTime();
		Long end = DateUtil.getEndTime();
		String hql = "from FacilityOrder where time>? and time<?";
		Object[] values = {start,end};
		List<TripOrder> tripOrder = tripOrderDAO.getListByHQL(hql, values);
		String payNo = ""+(tripOrder.size()+1);
//		int len = payNo.length();
		StringBuffer sb = new StringBuffer();
//		for(int i=len;i<5;i++){
//			sb.append("0");
//		}
//		sb.append(payNo);
		t.setPayNo(DateUtil.format(new Date(), "YYYYMMddHHmmss")+sb.toString());
		tripOrderDAO.saveOrUpdate(t);
	}

	@Override
	public void delete(TripOrder t) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public void update(TripOrder t) {
		// TODO Auto-generated method stub
		tripOrderDAO.update(t);
	}

	@Transactional
	@Override
	public TripOrder getTripOrder(Long oederId) {
		// TODO Auto-generated method stub
		String hql = "from FacilityOrder where oederId=?";
		Object[] v = {oederId};
		return tripOrderDAO.getByHQL(hql, v);
	}
	
	@Transactional
	@Override
	public List<TripOrder> list() {
		// TODO Auto-generated method stub
		return tripOrderDAO.getListByHQL("from TripOrder order by id desc", null);
	}

	@Transactional
	@Override
	public TripOrder getByPayNo(String payNo) {
		// TODO Auto-generated method stub
		return tripOrderDAO.getByHQL("from TripOrder where payNo="+payNo, null);
	}

}
