package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.ApartmentDAO;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.entity.User;

@Repository
public class ApartmentDAOImpl implements ApartmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addApartment(Apartment apartment) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.saveOrUpdate(apartment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public int updateApartment(Apartment apartment) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.update(apartment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<Apartment> listApartments() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Apartment> list = session.createQuery("from Apartment").list();
		return list;
	}

	@Override
	public Apartment getApartmentById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Apartment.class);
		c.add(Restrictions.eq("id", id));
		c.setFetchMode("rooms", FetchMode.JOIN).setFetchMode("facilities", FetchMode.JOIN)
				.setFetchMode("features", FetchMode.JOIN).setFetchMode("prices", FetchMode.JOIN)
				.setFetchMode("apartmentType", FetchMode.JOIN);
		return (Apartment) c.uniqueResult();
	}

	@Override
	public int removeApartment(long id) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Apartment apartment = (Apartment) session.load(Apartment.class, new Long(id));
			if (null != apartment) {
				session.delete(apartment);
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}
