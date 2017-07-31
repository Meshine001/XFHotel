package com.xfhotel.hotel.service;

import java.util.List;
import java.util.Map;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.House;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.SearchForm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ApartmentService extends BaseService<Apartment, Long> {

	public Apartment add(String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se,String te_se_class, String jia_ju,String jia_ju_class, String wei_yu,String wei_yu_class, String can_chu,String can_chu_class,
			String pei_tao,String pei_tao_class, String zou_bian,String zou_bian_class, String qi_ta, String qi_ta_class,String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge ,String  VR_di_zhi);

	public Apartment update(Long id,String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se,String te_se_class, String jia_ju,String jia_ju_class, String wei_yu,String wei_yu_class, String can_chu,String can_chu_class,
			String pei_tao,String pei_tao_class, String zou_bian,String zou_bian_class, String qi_ta, String qi_ta_class,String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge,String  VR_di_zhi);

	public List<House> getSpHouse(Long start, Long end, Long id);
	
	public JSONArray getHomeApartments();
	
	public JSONArray getHomeApartments1();

	public JSONObject getApartmentById(Long id);

	public JSONArray get2MonthPrices(Long id, String startDate);

	public JSONArray get7DaysPrices(Long id, String startDate);
	
	public List<Price> getSpPrices(Long start, Long end, Long id);

	public Price getSpPrice(Long id, Long date);

	public void setSpPrice(Price price);
	
	public List<Apartment> sort(List<Apartment> list, SearchForm searchData);

	PageResults<JSONObject> getApartmentPage(List<Apartment> list, int currentPage);
	// public JSONObject search(String content,Apartment apartment);
	
	// public List findApartment(String content, Apartment apartment);

	// public PageResults<Map> getPage(int page);

	public Map<String, Object> caculatePrice(String startTime, String endTime, Long apartmentId);
	
	public JSONObject createOrderMoudle(String startTime, String endTime, Long apartmentId);
	
	public Apartment modify(Apartment c, long id);
	
	public List<Apartment> landlord(Long affiliation);
	
	public double GetDistance(double lat1, double lng1, double lat2, double lng2) ;
	
	
}
