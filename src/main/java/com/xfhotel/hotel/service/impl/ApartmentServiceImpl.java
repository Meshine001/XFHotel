package com.xfhotel.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import com.xfhotel.hotel.dao.FacilityDAO;
import com.xfhotel.hotel.dao.impl.ApartmentDAOImpl;
import com.xfhotel.hotel.dao.impl.FacilityDAOImpl;
import com.xfhotel.hotel.dao.impl.PriceDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.support.PageResults;

@Service
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	ApartmentDAOImpl apartmentDAO;

	@Autowired
	FacilityDAOImpl facilityDAO;
	
	@Autowired
	PriceDAOImpl priceDAO;

	@Override
	@Transactional
	public String add(Apartment apartment) {
		// TODO Auto-generated method stub
		// validation
		if (apartmentDAO.addApartment(apartment) == 1)
			return "";
		return "";
	}

	@Override
	@Transactional
	public Apartment findById(long id) {
		// TODO Auto-generated method stub
		return apartmentDAO.getApartmentById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map getApartmentInfo(long id) {
		// TODO Auto-generated method stub
		Apartment apartment = apartmentDAO.getApartmentById(id);
		Map map = apartment.toMap();
		List<Map> facilities = new ArrayList<Map>();
		String[] ids = (String[]) map.get("facilities");
		for (String i : ids) {
			Map f = null;
			Facility fa = facilityDAO.get(Long.valueOf(i));
			if (fa == null)
				continue;
			f = fa.toMap();
			facilities.add(f);
		}
		map.put("facilityEntity", facilities);
		return map;
	}

	@Override
	@Transactional
	public List findApartment(String content, Apartment apartment) {
		// TODO Auto-generated method stub
		return apartmentDAO.findApartment(content, apartment);
	}

	@Transactional
	@Override
	public void update(Apartment apartment) {
		apartmentDAO.update(apartment);
	}

	
	@Transactional
	@Override
	public List list() {
		// TODO Auto-generated method stub
		List<Apartment> list = apartmentDAO.listApartments();
		List<Map> apartments = new ArrayList<Map>();
		for(Apartment a:list){
			apartments.add(getApartmentInfo(a.getId()));
		}
		return apartments;
	}
	
	
	@Transactional
	@Override
	public void delete(Apartment apartment) {
		apartmentDAO.delete(apartment);
	}

	@Transactional
	@Override
	public void setSpPrice(Price price) {
		priceDAO.saveOrUpdate(price);
	}
	
	@Transactional
	@Override
	public List<Map> getSpPrices(Long start, Long end,Apartment apartment) {
		String hqlString = "from Price where apartment=? and date >=? and date <=?";
		Object[] values = {apartment,start,end};
		List<Price> prices = priceDAO.getListByHQL(hqlString, values);
		List<Map> ps = new ArrayList<Map>();
		for(Price p:prices){
			ps.add(p.toMap());
		}
		return ps;
	}

	@Transactional
	@Override
	public Price getSpPrice(Apartment apartment,Long date) {
		String hqlString = "from Price where apartment=? and date =?";
		Object[] values = {apartment,date};
		return priceDAO.getByHQL(hqlString, values);
	}
	
	@Transactional
	@Override
	public PageResults<Map> getPage(int page) {
		PageResults<Apartment> results = apartmentDAO.findPageByFetchedHql("from Apartment", "select count(*) from Apartment", page, 5, null);
		PageResults<Map> infos = new PageResults<Map>();
		List<Map> m = new ArrayList<Map>();
		for(Apartment a:results.getResults()){
			m.add(a.toMap());
		}
		infos.setCurrentPage(results.getCurrentPage());
		infos.setPageCount(results.getPageCount());
		infos.setPageNo(results.getPageNo());
		infos.setResults(m);
		infos.setPageSize(results.getPageSize());
		infos.setTotalCount(results.getTotalCount());
		
		return infos;
	}

}
