package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Order;

public interface OrderService {
	
	public void add(Order o);
	public void update(Order o);
	public void delete(Order o);
	public Order get(Long id);
	public Order postOrder(Long cusId, String description, Long roomId, String cusName, String cusTel,
			String otherCusName, String otherCusIdCard, String cusIdCard, String personal, String startTime,
			String endTime, Integer totalDay, String price, String totalPrice, String preferential, boolean needFapiao,
			String apartmentType);
	public Order getByPayNo(String payNo);
	public List<Order> list(int type);
	public List<Order> listDiedOrders(int type);
	public List<Order> getCustomerOrders(Long cId,int type);
	public List<Order> checkAvailable(Long id,String startTime,String endTime);
	public boolean isTimeOut(Order o);
	public void refreshAll();
	public void refresh(Long cId);
	public List<Order> search(Long cId, int category, int type, String startDate, String endDate, int range);
}
