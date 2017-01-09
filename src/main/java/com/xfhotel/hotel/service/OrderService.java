package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Order;

public interface OrderService {
	
	public void add(Order o);
	public void update(Order o);
	public void delete(Order o);
	public Order get(Long id);
	public List<Order> list(int type);
	public List<Order> listDiedOrders(int type);
	public List<Order> getCustomerOrders(Long cId,int type);
		
}
