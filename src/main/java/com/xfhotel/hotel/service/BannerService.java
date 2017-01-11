package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Banner;

public interface BannerService extends BaseService<Banner>{
	public List<Banner> getHomeBanner(); 
}
