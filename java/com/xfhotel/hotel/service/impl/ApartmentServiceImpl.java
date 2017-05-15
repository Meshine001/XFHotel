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

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.FacilityDAO;
import com.xfhotel.hotel.dao.FeatureDAO;
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
import com.xfhotel.hotel.support.SearchForm;
import com.xfhotel.hotel.support.TimeUtil;

@Service
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	ApartmentDAOImpl apartmentDAO;

	@Autowired
	FacilityDAOImpl facilityDAO;
	
	@Autowired
	FeatureDAO featureDAO;
	
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
		
		List<Map> features = new ArrayList<Map>();
		String[] ids1 = (String[]) map.get("features");
		for (String i : ids1) {
			Map f = null;
			Feature fa = featureDAO.findById(Long.valueOf(i));
			if (fa == null)
				continue;
			f = fa.toMap();
			features.add(f);
		}
		map.put("featureEntity", features);
		System.out.println(features);
		
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

	/**
	 * 计算价格
	 */
	@Transactional
	@Override
	public Map<String, Object> caculatePrice(String startTime, String endTime, Long apartmentId) {
		Map<String, Object> info = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Apartment apartment = apartmentDAO.get(apartmentId);
		Double sum = 0.00D;
		Double cashPledge = apartment.getYajin();
		List<String> days = TimeUtil.getBetweenDays(startTime, endTime);
		for (int i = 0; i < days.size() - 1; i++) {
			Price p = getSpPrice(apartment, TimeUtil.getDateLong(days.get(i)));
			Double pp = null;
			if (p != null) {// 有特殊价格
				pp = p.getPrice();
			} else {
				pp = Double.valueOf(apartment.getPrices());
			}
			if (i == 0) {
				sb.append(pp);
			} else {
				sb.append("@" + pp);
			}
			sum += pp;
		}
		sum += cashPledge;
		sb.append("@"+cashPledge);
		info.put("price", sb.toString());
		info.put("cashPledge", cashPledge);
		info.put("totalPrice", sum);
		info.put("capacity", apartment.getCapacity());
		return info;
	}

	@Override
	public PageResults<Map> getApartmentPage(List<Map> list, int currentPage) {
		// TODO Auto-generated method stub
			PageResults<Map> page = new PageResults<Map>();
			int totalCount = list.size();
			int pageSize = 5;
			int t = totalCount/pageSize;
			int pageCount = t*pageSize < totalCount ?t+1:t;
			int pageNo = currentPage<pageCount?currentPage+1:currentPage;
			page.setTotalCount(totalCount);
			page.setPageSize(pageSize);
			page.setPageCount(pageCount);
			page.setPageNo(pageNo);
			page.setCurrentPage(currentPage);
			int m = (currentPage-1)*pageSize;
			int n = m+pageSize;
			List<Map> results = new ArrayList<Map>();
			for(int i = m;i<totalCount && i<n;i++){
				results.add(list.get(i));
			}
			page.setResults(results);
			return page;
		}

	@Override
	public List sort(List list, SearchForm searchData) {
		// TODO Auto-generated method stub
			double rates[] = new double[list.size()];
			for(int i=0; i<list.size(); i++){ 
				Object obj = list.get(i);
				rates[i] = searchData.rate((Map) obj);
			}
			
			for(int i=0; i<list.size(); i++){
				for(int j=i+1; j<list.size(); j++){
					int check=0;
					if( rates[i] == rates[j] ){
						switch(searchData.getSortType()){
						case 0:{
							Map obj1 = (Map) list.get(i);
							Map obj2 = (Map) list.get(j);
							//compare
							break;
						}
						case 1:{
							Map obj1 = (Map) list.get(i);
							Map obj2 = (Map) list.get(j);
							//compare
							double price1 = Double.valueOf(((String[])obj1.get("prices"))[0]);
							double price2 = Double.valueOf(((String[])obj2.get("prices"))[0]);
							if( price1>price2 )
								check=1;
							break;
						}
						case 2:{
							Map obj1 = (Map) list.get(i);
							Map obj2 = (Map) list.get(j);
							//compare
							double square1 = (Double)obj1.get("square");
							double square2 = (Double)obj2.get("square");
							if( square1<square2 )
								check=1;
							break;
						}
						}
					}
					if( rates[i] < rates[j] || rates[i] == rates[j] && check==1){
						Object tmp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, tmp);
						double t = rates[i];
						rates[i] = rates[j];
						rates[j] = t;
					}
				}
			}
			return list;
		
	}

}
