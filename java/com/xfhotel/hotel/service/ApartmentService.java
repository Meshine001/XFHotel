package com.xfhotel.hotel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.support.PageResults;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ApartmentService extends BaseService<Apartment, Long> {

	public Apartment add(String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se, String jia_ju, String wei_yu, String can_chu,
			String pei_tao, String zou_bian, String qi_ta, String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge);
	
	public Apartment update(Long id,String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se, String jia_ju, String wei_yu, String can_chu,
			String pei_tao, String zou_bian, String qi_ta, String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge);

	public JSONArray getHomeApartments();

	public JSONObject getApartmentById(Long id);

	public JSONArray get2MonthPrices(Long id, String startDate);

	public JSONArray get7DaysPrices(Long id, String startDate);

	public List<Price> getSpPrices(Long start, Long end, Long id);
	
	public Price getSpPrice(Long id,Long date);
	 public void setSpPrice(Price price);

	// public JSONObject search(String content,Apartment apartment);
	//
	// public List findApartment(String content, Apartment apartment);

	// public PageResults<Map> getPage(int page);



	// public Map<String, Object> caculatePrice(String startTime, String
	// endTime, Long apartmentId);
}
