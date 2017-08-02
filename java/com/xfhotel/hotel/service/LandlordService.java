package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Landlord;

public interface LandlordService extends BaseService<Landlord, Long>{
	public Landlord getCustomer(Long uId);
	public List<Landlord> getLandlord();
}
