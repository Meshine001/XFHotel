package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.FitnessDAOIpml;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Fitness;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FitnessService;

@Service
public class FitnessServiceImpl implements FitnessService{

	@Autowired
	FitnessDAOIpml fitnessDAO;

	@Transactional
	@Override
	public Fitness findById(Long id) {
		// TODO Auto-generated method stub
		return fitnessDAO.get(id);
	}

	@Transactional
	@Override
	public void add(Fitness t) {
		// TODO Auto-generated method stub
		fitnessDAO.save(t);
	}

	@Transactional
	@Override
	public void delete(Fitness t) {
		// TODO Auto-generated method stub
		fitnessDAO.delete(t);
	}

	@Transactional
	@Override
	public void update(Fitness t) {
		// TODO Auto-generated method stub
		fitnessDAO.update(t);
	}

	@Transactional
	@Override
	public List<Fitness> list() {
		// TODO Auto-generated method stub
		return fitnessDAO.getListByHQL("from Fitness order by id desc", null);
	}
	
	@Transactional
	@Override
	public List<Fitness> getlist(long id) {
		String hql = "from Fitness where merchant=?";
		Object[] v = {id};
		return fitnessDAO.getListByHQL(hql, v);
	}
	
	

}
