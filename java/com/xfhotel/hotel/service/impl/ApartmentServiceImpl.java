package com.xfhotel.hotel.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.TimeUtil;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	ApartmentDAOImpl apartmentDAO;
	@Autowired
	PriceDAOImpl priceDAO;
	@Autowired
	OrderService orderService;

	@Transactional
	@Override
	public Apartment findById(Long id) {
		// TODO Auto-generated method stub
		return apartmentDAO.get(id);
	}

	@Override
	public void add(Apartment t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Apartment t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Apartment t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Apartment> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public Apartment add(String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se, String jia_ju, String wei_yu, String can_chu,
			String pei_tao, String zou_bian, String qi_ta, String pic1, String[] pic2, String[] pic3,String lei_xing,
			String jia_ge) {
		
		Apartment apartment = new Apartment();
		
		JSONObject basic_info = new JSONObject();
		

		basic_info.put("suo_di_zhi", suo_di_zhi);
		basic_info.put("cao_xiang", cao_xiang);
		basic_info.put("mian_ji", mian_ji);
		basic_info.put("shi", shi);
		basic_info.put("ting", ting);
		basic_info.put("wei", wei);
		basic_info.put("reng_shu", reng_shu);
		basic_info.put("chuang", chuang);
		basic_info.put("lei_xing", lei_xing);
		basic_info.put("jia_ge", jia_ge);
		
		apartment.setBasic_info(basic_info.toString());
		
		JSONObject position = new JSONObject();
		position.put("jing_du", jing_du);
		position.put("wei_du", wei_du);
		position.put("bd_wei_zhi", bd_wei_zhi);
		position.put("xa_wei_zhi", xa_wei_zhi);
		position.put("jie_dao", jie_dao);
		position.put("xiao_qu", xiao_qu);
		position.put("dan_yuan", dan_yuan);
		position.put("lou_ceng", lou_ceng);
		position.put("zong_lou_ceng", zong_lou_ceng);
		position.put("men_pai", men_pai);
		
		apartment.setPosition(position.toString());
		
		apartment.setDescription(miao_su);
		
		apartment.setTe_se(JSONArray.fromObject(te_se.split("，")).toString());
		apartment.setJia_ju(JSONArray.fromObject(jia_ju.split("，")).toString());
		apartment.setWei_yu(JSONArray.fromObject(wei_yu.split("，")).toString());
		apartment.setCan_chu(JSONArray.fromObject(can_chu.split("，")).toString());
		apartment.setPei_tao(JSONArray.fromObject(pei_tao.split("，")).toString());
		apartment.setZou_bian(JSONArray.fromObject(zou_bian.split("，")).toString());
		apartment.setQi_ta(JSONArray.fromObject(qi_ta.split("，")).toString());
		
		apartment.setHu_xing_tu(pic1);
		apartment.setFang_jian_tu(JSONArray.fromObject(pic2).toString());
		apartment.setXiao_qu_tu(JSONArray.fromObject(pic3).toString());
		
		
		apartmentDAO.save(apartment);
		
		return apartment;
	}

	@Transactional
	@Override
	public JSONArray getHomeApartments() {
		List<Apartment> apartments = apartmentDAO.getListByHQL("from Apartment where show_home=true",null);
		return JSONArray.fromObject(apartments);
	}
	
	/**
	 * 获取更详细的信息
	 */
	@Transactional
	@Override
	public JSONObject getApartmentById(Long id) {
		Apartment apartment = findById(id);
		JSONObject jo = apartment.toJson();
		JSONArray picShow = new JSONArray();
		for(Object s:jo.getJSONArray("fang_jian_tu").toArray()){
			picShow.add(s);
		}
		jo.discard("fang_jian_tu");
		for(Object s:jo.getJSONArray("xiao_qu_tu").toArray()){
			picShow.add(s);
		}
		jo.discard("xiao_qu_tu");
		jo.put("pic_show", picShow);
		return jo;
	}
	
	@Transactional
	@Override
	public JSONArray get2MonthPrices(Long id,String startDate) {
		Apartment apartment = findById(id);
		Long start = TimeUtil.getDateLong(startDate+" 12:00","yyyy-MM-dd hh:mm");
		Long end = TimeUtil.getDatePlusMonth(new Date(start), 2).getTime();
		//获得特殊价格
		List<Price> sp = getSpPrices(start, end, id);
		//构造价格序列
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(start));
		int curM = calendar.get(Calendar.MONTH);
		int curY = calendar.get(Calendar.YEAR);
		List<Date> allDates = TimeUtil.getAllDateInMonth(curY,curM);
		if(curM == 12){
			allDates.addAll(TimeUtil.getAllDateInMonth(curY+1,0));
		}else{
			allDates.addAll(TimeUtil.getAllDateInMonth(curY,curM+1));
		}
		
		List<Order> availableOrders = orderService.checkAvailable(
				id, 
				TimeUtil.getDateStr(start), 
				TimeUtil.getDateStr(end));
		
		JSONArray prices = new JSONArray();
		for(Date d:allDates){
			JSONObject info = new JSONObject();
			JSONObject details = new JSONObject();
//			details.put("price", apartment.getPrices());
			details.put("start", DateUtil.format(d, "yyyy-MM-dd"));
			details.put("pricetype", "normal");
			details.put("roomNum", "1");
			
			for(Price m:sp){
				String t = DateUtil.format(new Date(m.getDate()), "yyyy-MM-dd");
				if(DateUtil.format(d, "yyyy-MM-dd").equals(t)){
					details.put("price",m.getPrice());
				}
			}
			for(Order o:availableOrders){
				Long tt = d.getTime()+1000*60*60*12;
				if( tt>o.getStartTime() && tt<o.getEndTime()){
					details.put("roomNum", "0");
				}
			}
			info.put(DateUtil.format(d, "yyyy-MM-dd"), details);
			prices.add(info);
		}
		return prices;
	}

	@Transactional
	@Override
	public List<Price> getSpPrices(Long start, Long end, Long id) {
		String hqlString = "from Price where apartment_id=? and date >=? and date <=?";
		Object[] values = {id,start,end};
		return priceDAO.getListByHQL(hqlString, values);
	}

}
