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

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.User;

@Repository
public class ApartmentDAOImpl extends BaseDAOImpl<Apartment, Long>{

	@Autowired
	private SessionFactory sessionFactory;

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

	public List<Apartment> listApartments() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Apartment> list = session.createQuery("from Apartment").list();
		return list;
	}

	public Apartment getApartmentById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Apartment.class);
		c.add(Restrictions.eq("id", id));
		//c.setFetchMode("rooms", FetchMode.JOIN).setFetchMode("facilities", FetchMode.JOIN)
		//		.setFetchMode("features", FetchMode.JOIN);
		return (Apartment) c.uniqueResult();
	}

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

	public List findApartment(String content, Apartment apartment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Apartment.class);
		c.add(Restrictions.like("layout", apartment.getLayout().split("@")[0]+"@%@%@%"));
		if( !apartment.getType().equals("全部") )
			c.add(Restrictions.eq("type", apartment.getType()));
		if(apartment.getFeatures()!=null){
			String[] f = apartment.getFeatures();
			for(int i=0;i<f.length;i++){
				c.add(Restrictions.like("features", "%@"+i+"%"));
			}
		}
		if(apartment.getPrice_scope()!=null){
			c.add(Restrictions.eq("price_scope", apartment.getPrice_scope()));
		}
		if(apartment.getAddress()!=null){
			c.add(Restrictions.like("address", "%"+apartment.getAddress()+"%"));
		}
		//c.add(Restrictions.eq("id", id));
		//c.setFetchMode("rooms", FetchMode.JOIN).setFetchMode("facilities", FetchMode.JOIN)
		//		.setFetchMode("features", FetchMode.JOIN);
		return c.list();
	}

}
