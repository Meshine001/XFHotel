package com.xfhotel.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.PriceDAOImpl;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService{

	@Autowired
	PriceDAOImpl priceDAOImpl;
	
	@Override
	@Transactional
	public void add(Price price) {
		// TODO Auto-generated method stub
		priceDAOImpl.save(price);
	}

}
