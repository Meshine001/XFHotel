package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.LeaseTypeDAO;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.LeaseType;

@Repository
public class LeaseTypeDAOImpl implements LeaseTypeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<LeaseType> listLeaseTypes() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<LeaseType> list = session.createQuery("from LeaseType").list();
		return list;
	}

}
