package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.FeatureDAO;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	FeatureDAO featureDAO;
	
	@Override
	@Transactional
	public List<Feature> listFeatures() {
		// TODO Auto-generated method stub
		return featureDAO.listFeatures();
	}

	@Override
	@Transactional
	public Feature findById(Long id) {
		// TODO Auto-generated method stub
		return featureDAO.findById(id);
	}
}
