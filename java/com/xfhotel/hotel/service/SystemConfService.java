package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.SystemConfig;

public interface SystemConfService extends BaseService<SystemConfig, Long>{
	public SystemConfig getConfig();
}
