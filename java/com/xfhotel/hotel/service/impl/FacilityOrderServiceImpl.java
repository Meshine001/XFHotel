package com.xfhotel.hotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.FacilityOrderDAOImpl;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.FacilityOrder;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.FacilityOrderService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.support.DateUtil;



@Service
public class FacilityOrderServiceImpl implements FacilityOrderService{
	
	@Autowired
	FacilityOrderDAOImpl facilityOrderDAO;

	@Transactional
	@Override
	public FacilityOrder findById(Long id) {
		// TODO Auto-generated method stub
		return facilityOrderDAO.get(id);
	}
	
	@Transactional
	@Override
	public void add(FacilityOrder t) {
		// TODO Auto-generated method stub
		Long start = DateUtil.getStartTime();
		Long end = DateUtil.getEndTime();
		String hql = "from FacilityOrder where time>? and time<?";
		Object[] values = {start,end};
		List<FacilityOrder> facilityOrder = facilityOrderDAO.getListByHQL(hql, values);
		String payNo = ""+(facilityOrder.size()+1);
		int len = payNo.length();
		StringBuffer sb = new StringBuffer();
		for(int i=len;i<5;i++){
			sb.append("0");
		}
		sb.append(payNo);
		
		t.setPayNo(DateUtil.format(new Date(), "yyyyMMdd")+sb.toString());
		facilityOrderDAO.saveOrUpdate(t);
		facilityOrderDAO.save(t);
	}

	@Override
	public void delete(FacilityOrder t) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public void update(FacilityOrder t) {
		// TODO Auto-generated method stub
		facilityOrderDAO.update(t);
	}

	@Transactional
	@Override
	public FacilityOrder getFaultOrder(Long oederId) {
		// TODO Auto-generated method stub
		String hql = "from FacilityOrder where oederId=?";
		Object[] v = {oederId};
		return facilityOrderDAO.getByHQL(hql, v);
	}
	
	@Transactional
	@Override
	public List<FacilityOrder> list() {
		// TODO Auto-generated method stub
		return facilityOrderDAO.getListByHQL("from FacilityOrder order by id desc", null);
	}

	@Transactional
	@Override
	public FacilityOrder getByPayNo(String payNo) {
		// TODO Auto-generated method stub
		return facilityOrderDAO.getByHQL("from FacilityOrder where payNo="+payNo, null);
	}

}
