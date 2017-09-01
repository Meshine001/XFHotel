package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Fitness;


public interface FitnessService  extends BaseService<Fitness,Long>{
	public List<Fitness> getlist(long id);
}
