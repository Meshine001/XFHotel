package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.House;

public interface HouseService extends BaseService<House,Long>{
	public House getHouse(Long apartmentId,Long date);
	public List<House> getHouseId(Long apartmentId);
}
