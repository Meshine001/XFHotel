package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Clean;


public interface CleanService extends BaseService<Clean, Long> {

	public Clean get(Long id);
	public List<Clean> list();
	public Clean getClean(Long oederId);
	public List<Clean> getClean1(Long roomId);
	public List<Clean> getClean2(Long oederId);
}
