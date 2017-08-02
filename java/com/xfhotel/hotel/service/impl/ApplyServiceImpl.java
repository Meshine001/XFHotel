package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.ApplyDAOImpl;
import com.xfhotel.hotel.entity.Apply;
import com.xfhotel.hotel.service.ApplyService;

@Service
public class ApplyServiceImpl implements ApplyService{
	
	@Autowired
	ApplyDAOImpl applyDao ;

	@Transactional
	@Override
	public Apply findById(Long id) {
		// TODO Auto-generated method stub
		return applyDao.get(id);
	}
	
	@Transactional
	@Override
	public void add(Apply t) {
		// TODO Auto-generated method stub
		applyDao.save(t);
	}

	@Transactional
	@Override
	public void delete(Apply t) {
		// TODO Auto-generated method stub
		applyDao.delete(t);
	}

	@Transactional
	@Override
	public void update(Apply t) {
		// TODO Auto-generated method stub
		applyDao.update(t);
	}

	@Transactional
	@Override
	public List<Apply> list() {
		// TODO Auto-generated method stub
		return applyDao.getListByHQL("from Apply", null);
	}

}
