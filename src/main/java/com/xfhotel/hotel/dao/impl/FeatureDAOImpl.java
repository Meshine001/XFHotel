package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.FacilityDAO;
import com.xfhotel.hotel.dao.FeatureDAO;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;

@Repository
public class FeatureDAOImpl extends BaseDAOImpl<Feature, Long> implements FeatureDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Feature> listFeatures() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Feature> list = session.createQuery("from Feature").list();
		return list;
	}

	@Override
	public Feature findById(Long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Feature feature = (Feature) session.load(Feature.class, new Long(id));
		return feature;
	}
	


}
