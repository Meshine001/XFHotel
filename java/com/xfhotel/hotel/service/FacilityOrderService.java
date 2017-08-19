package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.FacilityOrder;

public interface FacilityOrderService extends BaseService<FacilityOrder, Long> {
	public List<FacilityOrder> getFaultOrder(Long oederId);
	public FacilityOrder getByPayNo(String payNo);
	public List<FacilityOrder> getFaultOrder1(Long apId);
	public List<FacilityOrder> list();
}
