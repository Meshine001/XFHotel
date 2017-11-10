package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.RestsDAOImopl;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Rests;
import com.xfhotel.hotel.service.RestsService;
import com.xfhotel.hotel.support.PageResults;

@Service
public class RestsServiceImpl implements RestsService {
	
	@Autowired
	RestsDAOImopl restsDao;
	

	@Transactional
	@Override
	public Rests findById(Long id) {
		// TODO Auto-generated method stub
		return restsDao.get(id);
	}

	@Transactional
	@Override
	public void add(Rests t) {
		// TODO Auto-generated method stub
		restsDao.saveOrUpdate(t);
	}

	@Transactional
	@Override
	public void delete(Rests t) {
		// TODO Auto-generated method stub
		restsDao.delete(t);
	}

	@Transactional
	@Override
	public void update(Rests t) {
		// TODO Auto-generated method stub
		restsDao.saveOrUpdate(t);
	}

	@Transactional
	@Override
	public List<Rests> list() {
		// TODO Auto-generated method stub
		return restsDao.getListByHQL("from Rests order by id desc", null);
	}

	@Transactional
	@Override
	public PageResults<Rests> getRests(int page) {
		// TODO Auto-generated method stub
		return restsDao.findPageByFetchedHql("from Rests order by id desc", "select count(*) from Rests", page, 10, null);
	}

}
