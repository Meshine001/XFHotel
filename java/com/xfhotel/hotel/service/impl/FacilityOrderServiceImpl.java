package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.FacilityOrderDAOImpl;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.FacilityOrder;
import com.xfhotel.hotel.service.FacilityOrderService;
import com.xfhotel.hotel.service.FacilityService;



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


}
