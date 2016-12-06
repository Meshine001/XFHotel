package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.FacilityDAO;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;

@Repository
public class FacilityDAOImpl implements FacilityDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Facility> listFacilities() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Facility> list = session.createQuery("from Facility").list();
		return list;
	}

}
