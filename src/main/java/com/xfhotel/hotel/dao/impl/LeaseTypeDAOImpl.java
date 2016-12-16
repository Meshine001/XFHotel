package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.LeaseType;

@Repository
public class LeaseTypeDAOImpl extends BaseDAOImpl<LeaseType, Long>{

	@Autowired
	SessionFactory sessionFactory;
	
	public List<LeaseType> listLeaseTypes() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<LeaseType> list = session.createQuery("from LeaseType").list();
		return list;
	}

	public List findApartmentTypeLeases(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<LeaseType> list = 	session.createQuery("from LeaseType where apartmentType.id=?").setLong(0, id).list();
		return null;
	}

}
