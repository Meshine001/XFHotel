package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Clean;


public interface CleanService extends BaseService<Clean, Long> {

	public Clean get(Long id);
}
