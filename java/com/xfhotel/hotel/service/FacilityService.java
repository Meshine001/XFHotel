package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Facility;

public interface FacilityService extends BaseService<Facility, Long>{
		public List<Facility> list();
		public List<Facility> list1(int classify);
		public List<Facility> getFacility(long oederId);
}
