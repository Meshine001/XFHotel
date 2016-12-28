package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAOImpl orderDAO;

	@Transactional
	@Override
	public void add(Order o) {
		orderDAO.saveOrUpdate(o);
	}

	@Transactional
	@Override
	public void update(Order o) {
		orderDAO.update(o);
	}

	@Transactional
	@Override
	public void delete(Order o) {
		orderDAO.delete(o);
	}

	@Transactional
	@Override
	public Order get(Long id) {
		return orderDAO.get(id);
	}

	@Transactional
	@Override
	public List<Order> list(int type) {

		return null;
	}

}
