package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.ApartmentTypeDAO;
import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.entity.Facility;

@Repository
public class ApartmentTypeDAOImpl implements ApartmentTypeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ApartmentType> listApartmentTypes() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<ApartmentType> list = session.createQuery("from ApartmentType").list();
		return list;
	}

	@Override
	public ApartmentType findById(Long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		ApartmentType apartmentType = (ApartmentType) session.createQuery("from ApartmentType where id=:id").setLong("id", id).list().get(0);
		return apartmentType;
	}

}
