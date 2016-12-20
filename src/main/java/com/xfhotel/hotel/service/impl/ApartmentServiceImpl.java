package com.xfhotel.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.ApartmentDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;


@Service
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	ApartmentDAOImpl apartmentDAO;

	@Override
	@Transactional
	public String add(Apartment apartment) {
		// TODO Auto-generated method stub
		//validation
		if( apartmentDAO.addApartment(apartment) == 1 )
			return "";
		return "";
	}

	@Override
	@Transactional
	public Apartment findById(long id) {
		// TODO Auto-generated method stub
		return apartmentDAO.getApartmentById(id);
	}

	@Override
	@Transactional
	public Map getApartmentInfo(long id) {
		// TODO Auto-generated method stub
		Apartment apartment = apartmentDAO.getApartmentById(id);
		Map map = apartment.toMap();
		
		ArrayList rooms = new ArrayList();
		Iterator itr = apartment.getRooms().iterator();
		while(itr.hasNext()){
			Room r = (Room) itr.next();
			rooms.add(r.toMap());
		}
		map.put("rooms", rooms);
		
		ArrayList facilities = new ArrayList();
		Iterator itfc = apartment.getFacilities().iterator();
		while(itfc.hasNext()){
			Facility f = (Facility) itfc.next();
			facilities.add(f.toMap());
		}
		map.put("facilities", facilities);
		
		ArrayList features = new ArrayList();
		Iterator itft = apartment.getFeatures().iterator();
		while(itft.hasNext()){
			Feature f = (Feature) itft.next();
			features.add(f.toMap());
		}
		map.put("features", features);
		
		ArrayList prices = new ArrayList();
		Iterator itp = apartment.getPrices().iterator();
		while(itp.hasNext()){
			Price p = (Price) itp.next();
			prices.add(p.toMap());
		}
		map.put("prices", prices);
		
		map.put("apartmentType", apartment.getApartmentType().getId());	
		return map;
	}

}
