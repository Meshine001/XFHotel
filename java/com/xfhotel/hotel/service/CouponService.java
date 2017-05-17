package com.xfhotel.hotel.service;

import java.util.List;


import com.xfhotel.hotel.entity.Coupon;



public interface CouponService extends BaseService<Coupon>{

	public List<Coupon> getCoupon(Long uId);
	
	public Coupon modify(Coupon c, long uId);
	
	public Coupon getCoupon2(long id);
	
	public void deleteById(long Id);
	

}
