package com.xfhotel.hotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.support.TimeUtil;

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
		if (type == Apartment.TYPE_ALL) {
			return orderDAO.getListByHQL("from Order", null);
		}
		String hql = "from Order where type=?";
		Integer[] values = new Integer[1];
		switch (type) {
		case Apartment.TYPE_APARTMENT:
			values[0] = Apartment.TYPE_APARTMENT;
		case Apartment.TYPE_PLAY_ROOM:
			values[0] = Apartment.TYPE_PLAY_ROOM;
		default:
			values[0] = Apartment.TYPE_HOTEL;
		}

		return orderDAO.getListByHQL(hql, values);
	}

	@Transactional
	@Override
	public List<Order> listDiedOrders(int type) {
		Long diedLine = new Date().getTime() - Constants.EFFECTIVE_ORDER_TIME_DURING;
		String hql = "from Order where type =? and time<" + diedLine;
		Integer[] values = new Integer[1];
		switch (type) {
		case Apartment.TYPE_HOTEL:
			values[0] = Apartment.TYPE_APARTMENT;
		default:
			values[0] = Apartment.TYPE_HOTEL;
		}
		return orderDAO.getListByHQL(hql, values);
	}

	@Transactional
	@Override
	public List<Order> getCustomerOrders(Long cId, int type) {
		if (type == Apartment.TYPE_ALL) {
			Object[] values = new Object[1];
			values[0] = cId;
			return orderDAO.getListByHQL("from Order where cusId=?", values);
		}
		String hql = "from Order where cusId=? and type=?";
		Object[] values = new Object[2];
		values[0] = cId;
		switch (type) {
		case Apartment.TYPE_APARTMENT:
			values[1] = Apartment.TYPE_APARTMENT;
			break;
		case Apartment.TYPE_PLAY_ROOM:
			values[1] = Apartment.TYPE_PLAY_ROOM;
			break;
		default:
			values[1] = Apartment.TYPE_HOTEL;
		}

		return orderDAO.getListByHQL(hql, values);
	}
	
	@Transactional
	@Override
	public List<Order> checkAvailable(Long roomId, String startTime, String endTime) {
		String hql = "from Order where roomId = ? and (status =? or status=?) and (startTime >? and endTime <?)";
		Object[] values = {roomId,Order.STATUS_ON_PAY,Order.STATUS_ON_LEASE,(TimeUtil.getDateLong(startTime)-1),(TimeUtil.getDateLong(endTime)+1)};
		return orderDAO.getListByHQL(hql, values);
	}

	@Override
	public boolean isTimeOut(Order o) {
		Long time = o.getTime();
		Long deadLine = time + Constants.EFFECTIVE_ORDER_TIME_DURING;
		Long now = new Date().getTime();
		if(now > deadLine)return true;
		return false;
	}


}
