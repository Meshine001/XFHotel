package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Tenant;
import com.xfhotel.hotel.support.Message;

public interface TenantService extends BaseService<Tenant, Long> {

	public Tenant getTenant(String userName , String password);
	
}
