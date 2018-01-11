package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CleanDAOImpl;
import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.service.CleanService;
import com.xfhotel.hotel.service.SystemConfService;

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
		return CleanDAO.get(id);
	}

	@Transactional
	@Override
	public void add(Clean t) {
		// TODO Auto-generated method stub
	
		CleanDAO.save(t);
	
	}
	@Transactional
	@Override
	public Clean getClean(Long oederId) {
		String hql = "from Clean where oederId=? order by id desc";
		Object[] v = {oederId};
		return CleanDAO.getByHQL(hql, v);
		}
	
	@Transactional
	@Override
	public List<Clean> getClean2(Long oederId) {
		String hql = "from Clean where oederId=? order by id desc";
		Object[] v = {oederId};
		return CleanDAO.getListByHQL(hql, v);
		}

	@Transactional
	@Override
	public List<Clean> getClean1(Long roomId) {
		String hql = "from Clean where roomId=? order by id desc";
		Object[] v = {roomId};
		return CleanDAO.getListByHQL(hql, v);
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
		return CleanDAO.getListByHQL("from Clean order by id desc", null);
	}
	
	@Transactional
	@Override
	public Clean get(Long id) {
		return CleanDAO.get(id);
	}

}
