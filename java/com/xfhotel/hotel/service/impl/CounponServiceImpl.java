package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CouponDAOImpl;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.service.CouponService;

@Service
public class CounponServiceImpl implements CouponService{
	@Autowired
	CouponDAOImpl couponDAO;
	
	@Transactional
	@Override
	public void add(Coupon coupon) {
		// TODO Auto-generated method stub
		couponDAO.saveOrUpdate(coupon);
	}

	@Override
	public void delete(Coupon t) {
		// TODO Auto-generated method stub
		couponDAO.delete(t);
		
	}

	@Override
	public void update(Coupon t) {
		// TODO Auto-generated method stub
		couponDAO.update(t);
		
	}

	@Override
	public List<Coupon> list() {
		// TODO Auto-generated method stub
		return couponDAO.getListByHQL("from Coupon", null);
		}

	@Transactional
	@Override
	public List<Coupon> getCoupon(Long uId) {
		String hql = "from Coupon where uId=?";
		Object[] v = {uId};
		return couponDAO.getListByHQL(hql, v);
		}
	@Transactional
	@Override
	public Coupon modify(Coupon c, long id) {
		// TODO Auto-generated method stub
		couponDAO.saveOrUpdate(c);
		return couponDAO.get(id);
		}
	@Transactional
	@Override
	public Coupon getCoupon2(long id) {
		// TODO Auto-generated method stub
		return couponDAO.get(id);
		}


	@Override
	public void deleteById(long Id) {
		// TODO Auto-generated method stub
		couponDAO.deleteById(Id);
		}
}
