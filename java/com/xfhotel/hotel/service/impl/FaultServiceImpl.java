package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.FaultDAOImpl;
import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Fault;
import com.xfhotel.hotel.service.FaultService;
import com.xfhotel.hotel.service.SystemConfService;

@Service
public class FaultServiceImpl implements FaultService{
	@Autowired
	FaultDAOImpl FaultDAO;
	
	@Autowired
	OrderDAOImpl orderDAO;
	
	@Autowired
	SystemConfService systemConfiService;
	

	@Override
	public Fault findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public void add(Fault t) {
		// TODO Auto-generated method stub
		FaultDAO.save(t);
	}

	@Override
	public void delete(Fault t) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	@Override
	public void update(Fault t) {
		// TODO Auto-generated method stub
		FaultDAO.update(t);
	}
	@Transactional
	@Override
	public Fault get(Long id) {
		// TODO Auto-generated method stub
		return FaultDAO.get(id);
	}
	@Transactional
	@Override
	public Fault getFault(Long oederId) {
		// TODO Auto-generated method stub
		String hql = "from Fault where oederId=?";
		Object[] v = {oederId};
		return FaultDAO.getByHQL(hql, v);
	}
	@Transactional
	@Override
	public List<Fault> list() {
		// TODO Auto-generated method stub
		return FaultDAO.getListByHQL("from Fault order by id desc", null);
	}

}
