package com.xfhotel.hotel.service;

import org.springframework.stereotype.Service;

import com.xfhotel.hotel.entity.Rests;
import com.xfhotel.hotel.support.PageResults;


public interface RestsService extends BaseService<Rests, Long>{

	public PageResults<Rests> getRests(int page);
}
