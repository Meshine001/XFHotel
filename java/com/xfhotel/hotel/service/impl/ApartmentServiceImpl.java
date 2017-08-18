package com.xfhotel.hotel.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.ApartmentDAOImpl;
import com.xfhotel.hotel.dao.impl.HouseDAOImpl;
import com.xfhotel.hotel.dao.impl.PriceDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.House;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.SystemConfService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.SearchForm;
import com.xfhotel.hotel.support.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	ApartmentDAOImpl apartmentDAO;
	@Autowired
	PriceDAOImpl priceDAO;
	@Autowired
	HouseDAOImpl houseDAO;
	@Autowired
	SystemConfService systemConfService;
	
	@Autowired
	OrderService orderService;
	
	
	private static double EARTH_RADIUS = 6371.393;  
    private static double rad(double d)  
    {  
       return d * Math.PI / 180.0;  
    } 
	
	@Transactional
	@Override
	public Apartment findById(Long id) {
		return apartmentDAO.get(id);
	}
	
	@Transactional
	@Override
	public void add(Apartment t) {
		apartmentDAO.save(t);
	}
	
	@Transactional
	@Override
	public void delete(Apartment t) {
		apartmentDAO.delete(t);
	}

	@Transactional
	@Override
	public void update(Apartment t) {
		apartmentDAO.update(t);
	}

	@Transactional
	@Override
	public List<Apartment> list() {
		return apartmentDAO.listApartments();
	}

	public Apartment saveOrUpdate(Long id,String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se,String te_se_class, String jia_ju,String jia_ju_class, String wei_yu,String wei_yu_class, String can_chu,String can_chu_class,
			String pei_tao,String pei_tao_class, String zou_bian,String zou_bian_class, String qi_ta, String qi_ta_class,String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge ,String  VR_di_zhi) {

		Apartment apartment;
		
		if (null == id) {// add
			apartment = new Apartment();
		} else {
			apartment = apartmentDAO.get(id);
		}
		
		JSONObject basic_info = new JSONObject();

		basic_info.put("suo_di_zhi", suo_di_zhi);
//		basic_info.put("cao_xiang", cao_xiang);
		basic_info.put("mian_ji", mian_ji);
		basic_info.put("shi", shi);
		basic_info.put("ting", ting);
		basic_info.put("wei", wei);
		basic_info.put("reng_shu", reng_shu);
		basic_info.put("chuang", chuang);
		basic_info.put("lei_xing", lei_xing);
		basic_info.put("jia_ge", jia_ge);
		basic_info.put("yang_tai", yang_tai);
		apartment.setPanorama(VR_di_zhi);
		apartment.setBasic_info(basic_info.toString());
		JSONObject position = new JSONObject();
		position.put("jing_du", jing_du);
		position.put("wei_du", wei_du);
		position.put("bd_wei_zhi", bd_wei_zhi);
		position.put("xa_wei_zhi", xa_wei_zhi);
		position.put("jie_dao", jie_dao);
		position.put("xiao_qu", xiao_qu);
		position.put("lou_hao", lou_hao);
		position.put("dan_yuan", dan_yuan);
		position.put("lou_ceng", lou_ceng);
		position.put("zong_lou_ceng", zong_lou_ceng);
		position.put("men_pai", men_pai);
		
		apartment.setPosition(position.toString());
		
		apartment.setDescription(miao_su);

		apartment.setTe_se(JSONArray.fromObject(te_se.trim().split("，")).toString());
		apartment.setJia_ju(JSONArray.fromObject(jia_ju.trim().split("，")).toString());
		apartment.setWei_yu(JSONArray.fromObject(wei_yu.trim().split("，")).toString());
		apartment.setCan_chu(JSONArray.fromObject(can_chu.trim().split("，")).toString());
		apartment.setPei_tao(JSONArray.fromObject(pei_tao.trim().split("，")).toString());
		apartment.setZou_bian(JSONArray.fromObject(zou_bian.trim().split("，")).toString());
		apartment.setQi_ta(JSONArray.fromObject(qi_ta.trim().split("，")).toString());

		apartment.setTe_se_class(JSONArray.fromObject(te_se_class.trim().split("，")).toString());
		apartment.setJia_ju_class(JSONArray.fromObject(jia_ju_class.trim().split("，")).toString());
		apartment.setWei_yu_class(JSONArray.fromObject(wei_yu_class.trim().split("，")).toString());
		apartment.setCan_chu_class(JSONArray.fromObject(can_chu_class.trim().split("，")).toString());
		apartment.setPei_tao_class(JSONArray.fromObject(pei_tao_class.trim().split("，")).toString());
		apartment.setZou_bian_class(JSONArray.fromObject(zou_bian_class.trim().split("，")).toString());
		apartment.setQi_ta_class(JSONArray.fromObject(qi_ta_class.trim().split("，")).toString());
		apartment.setHu_xing_tu(pic1);
		apartment.setFang_jian_tu(JSONArray.fromObject(pic2).toString());
		apartment.setXiao_qu_tu(JSONArray.fromObject(pic3).toString());
		apartmentDAO.saveOrUpdate(apartment);
		return apartment;
	}

	@Transactional
	@Override
	public Apartment add(String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se,String te_se_class, String jia_ju,String jia_ju_class, String wei_yu,String wei_yu_class, String can_chu,String can_chu_class,
			String pei_tao,String pei_tao_class, String zou_bian,String zou_bian_class, String qi_ta, String qi_ta_class,String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge ,String VR_di_zhi) {
		return saveOrUpdate(null, jing_du, wei_du, bd_wei_zhi, xa_wei_zhi, jie_dao, xiao_qu, lou_hao, dan_yuan, lou_ceng, zong_lou_ceng, men_pai, suo_di_zhi, cao_xiang, mian_ji, shi, ting, wei, yang_tai, reng_shu, chuang, miao_su, te_se, te_se_class, jia_ju, jia_ju_class, wei_yu, wei_yu_class, can_chu, can_chu_class, pei_tao, pei_tao_class, zou_bian, zou_bian_class, qi_ta, qi_ta_class, pic1, pic2, pic3, lei_xing, jia_ge,VR_di_zhi);
	}

	@Transactional
	@Override
	public JSONArray getHomeApartments() {
		List<Apartment> apartments = apartmentDAO.getListByHQL("from Apartment where show_home=true order by sort asc", null);
		return JSONArray.fromObject(apartments);
	}
	
	@Transactional
	@Override
	public JSONArray getHomeApartments1() {
		List<Apartment> apartments = apartmentDAO.getListByHQL("from Apartment order by id desc" , null);
		return JSONArray.fromObject(apartments);
	}
	
	@Transactional
	@Override
	public List<Apartment> getApartments1() {
		return apartmentDAO.getListByHQL("from Apartment order by id desc" , null);
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
		for (Object s : jo.getJSONArray("fang_jian_tu").toArray()) {
			picShow.add(s);
		}
		jo.discard("fang_jian_tu");
		for (Object s : jo.getJSONArray("xiao_qu_tu").toArray()) {
			picShow.add(s);
		}
		jo.discard("xiao_qu_tu");
		jo.put("pic_show", picShow);
		return jo;
	}
	
	@Transactional
	@Override
	public JSONArray get2MonthPrices(Long id, String startDate) {
		Apartment apartment = findById(id);
		Long start = TimeUtil.getDateLong(startDate + " 12:00", "yyyy-MM-dd hh:mm");
		Long end = TimeUtil.getDatePlusMonth(new Date(start), 2).getTime();
		// 获得特殊价格
		List<Price> sp = getSpPrices(start, end, id);
		List<House> house = getSpHouse(start, end, id);
		// 构造价格序列
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(start));
		int curM = calendar.get(Calendar.MONTH);
		int curY = calendar.get(Calendar.YEAR);
		List<Date> allDates = TimeUtil.getAllDateInMonth(curY, curM);
		if (curM == 12) {
			allDates.addAll(TimeUtil.getAllDateInMonth(curY + 1, 0));
		} else {
			allDates.addAll(TimeUtil.getAllDateInMonth(curY, curM + 1));
		}
		JSONArray prices = new JSONArray();
//		List<Order> availableOrders = orderService.checkAvailable(id, TimeUtil.getDateStr(start),
//				TimeUtil.getDateStr(end));
		for (Date d : allDates) {
			JSONObject info = new JSONObject();
			JSONObject details = new JSONObject();
			details.put("price", apartment.getBasic_info().getString("jia_ge"));
			details.put("start", DateUtil.format(d, "yyyy-MM-dd"));
			details.put("pricetype", "normal");
			details.put("roomNum", "1");
			for (Price m : sp) {
				if (DateUtil.format(d, "yyyy-MM-dd").equals(m.getDate())) {
					details.put("price", m.getPrice());
				}
			}
			for(House h :house){
				Long tt = d.getTime() + 1000 * 60 * 60 * 12;
				
//				System.out.println(tt);
//				System.out.println(tt+"d"+h.getDate());
//				Date date = new Date(tt);  
//				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				System.out.println(sd.format(date));
				if(h.getState()==0&& h.getDate().equals(tt)){
					details.put("roomNum", "0");
				}
			}
//			for (Order o : availableOrders) {
//			//	Long tt = d.getTime() + 1000 * 60 * 60 * 14;
////				Date date = new Date(tt);  
////				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////				System.out.println(sd.format(date));
//				if (o.getStatus()==0&&o.getStatus()==3&&o.getStatus()==5) {
//					details.put("roomNum", "1");
//				}
//			}
			info.put(DateUtil.format(d, "yyyy-MM-dd"), details);
			prices.add(info);
		}
		return prices;
	}

	@Transactional
	@Override
	public List<Price> getSpPrices(Long start, Long end, Long id) {
		String hqlString = "from Price where apartment_id=? and date >=? and date <=?";
		Object[] values = { id, start, end };
		return priceDAO.getListByHQL(hqlString, values);
	}
	
	@Transactional
	@Override
	public JSONArray get7DaysPrices(Long id, String startDate) {
		JSONArray twoMonthPrices = get2MonthPrices(id, startDate);
		JSONArray sevenDaysPrices = new JSONArray();
		int s = Integer.parseInt(startDate.substring(startDate.length() - 2, startDate.length())) - 1;
		Iterator<Object> iterator = twoMonthPrices.iterator();
		int i = 0;
		while (iterator.hasNext() && i < (s + 6)) {
			if (i < s) {
				iterator.next();
				i++;
				continue;
			}
			
			JSONObject info = JSONObject.fromObject(iterator.next());
			String date = (String) info.keys().next();
			JSONObject m = new JSONObject();
			m.put("date", date.substring(date.length() - 2, date.length()));
			JSONObject details = info.getJSONObject(date);
			m.put("price", details.get("price"));
			sevenDaysPrices.add(m);
			i++;
		}
		
		return sevenDaysPrices;
	}

	@Transactional
	@Override
	public Apartment update(Long id,String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se,String te_se_class, String jia_ju,String jia_ju_class, String wei_yu,String wei_yu_class, String can_chu,String can_chu_class,
			String pei_tao,String pei_tao_class, String zou_bian,String zou_bian_class, String qi_ta, String qi_ta_class,String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge ,String VR_di_zhi) {
		
		return saveOrUpdate(id, jing_du, wei_du, bd_wei_zhi, xa_wei_zhi, jie_dao, xiao_qu, lou_hao, dan_yuan, lou_ceng, zong_lou_ceng, men_pai, suo_di_zhi, cao_xiang, mian_ji, shi, ting, wei, yang_tai, reng_shu, chuang, miao_su, te_se, te_se_class, jia_ju, jia_ju_class, wei_yu, wei_yu_class, can_chu, can_chu_class, pei_tao, pei_tao_class, zou_bian, zou_bian_class, qi_ta, qi_ta_class, pic1, pic2, pic3, lei_xing, jia_ge ,VR_di_zhi);

	}
	
	@Transactional
	@Override
	public Price getSpPrice(Long id, Long date) {
		String hqlString = "from Price where apartment_id=? and date =?";
		Object[] values = {id,date};
		return priceDAO.getByHQL(hqlString, values);
	}
	
	@Transactional
	@Override
	public void setSpPrice(Price price) {
		priceDAO.saveOrUpdate(price);
	}
	
	@Transactional
	@Override
	public Map<String, Object> caculatePrice(String startTime, String endTime, Long apartmentId) {
		Map<String, Object> info = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Apartment apartment = apartmentDAO.get(apartmentId);
		Double sum = 0.00D;
		Double cashPledge = systemConfService.getConfig().getYa_jin();
		List<String> days = TimeUtil.getBetweenDays(startTime, endTime);
		for (int i = 0; i < days.size() - 1; i++) {
			Price p = getSpPrice(apartmentId, TimeUtil.getDateLong(days.get(i)));
			Double pp = null;
			if (p != null) {// 有特殊价格
				pp = p.getPrice();
			} else {
				pp = Double.valueOf(apartment.getBasic_info().getDouble("jia_ge"));
			}
			if (i == 0) {
				sb.append(pp);
			} else {
				sb.append("@" + pp);
			}
			sum += pp;
		}
		info.put("Price", sum);
		sum += cashPledge;
		sb.append("@"+cashPledge);
		info.put("price", sb.toString());
		info.put("cashPledge", cashPledge);
		info.put("totalPrice", sum);
		info.put("capacity", apartment.getBasic_info().get("reng_shu"));
		return info;
	}
	
	/**
	 * 排序
	 */
	@Override
	public List<Apartment> sort(List<Apartment> list, SearchForm searchData) {
//		ArrayList<Object> list1 = new ArrayList<Object>();
//		for(Apartment apartment :list ){
//			
//			if(searchData.getLayout()==0){
//				list.add(apartment);
//			} else if(apartment.getBasic_info().get("shi")==searchData.getLayout()){
//				list.add(apartment);
//			} else if(apartment.getBasic_info().get("shi")==searchData.getArea()){
//				list.add(apartment);
//			}
//		}
		
		// TODO Auto-generated method stub
		 return list;
	}
	
	
	@Override
	public PageResults<JSONObject> getApartmentPage(List<Apartment> list, int currentPage) {
		
		PageResults<JSONObject> page = new PageResults<JSONObject>();
		int totalCount = list.size();
		int pageSize = 5;
		int t = totalCount / pageSize;
		int pageCount = t * pageSize < totalCount ? t + 1 : t;
		int pageNo = currentPage < pageCount ? currentPage + 1 : currentPage;
		page.setTotalCount(totalCount);
		page.setPageSize(pageSize);
		page.setPageCount(pageCount);
		page.setPageNo(pageNo);
		page.setCurrentPage(currentPage);
		int m = (currentPage - 1) * pageSize;
		int n = m + pageSize;
		List<JSONObject> results = new ArrayList<JSONObject>();
		for (int i = m; i < totalCount && i < n; i++) {
			results.add(list.get(i).toJson());
		}
		page.setResults(results);
		return page;
	}
	
	
	@Transactional
	@Override
	public JSONObject createOrderMoudle(String startTime, String endTime, Long apartmentId) {
		JSONObject moudle = new JSONObject();
		moudle.put("oStart", startTime);
		moudle.put("oEnd", endTime);
		Map<String, Object> priceInfo = caculatePrice(startTime, endTime, apartmentId);
//		System.out.println(priceInfo);0
		double sum = (Double) priceInfo.get("totalPrice");
		double yj = (Double) priceInfo.get("cashPledge");
		try {
			int Day = TimeUtil.daysBetween(startTime, endTime);
			moudle.put("oTotalDay", Day);
			if(Day>=3&&Day<7){
				sum-=yj;
				sum*=0.95;
				sum+=yj;
			}else if(Day>=7&&Day<30){
				sum-=yj;
				sum*=0.9;
				sum+=yj;
			}else if(Day>=30){
				sum-=yj;
				sum*=0.8;
				sum+=yj;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moudle.put("oPrice", priceInfo.get("price"));
		moudle.put("oTotalPrice", sum);
		moudle.put("oCashPledge", priceInfo.get("cashPledge"));
		moudle.put("oPreferential", "");
		moudle.put("price", priceInfo.get("Price"));
		moudle.put("capacity", priceInfo.get("capacity"));
		return moudle;
	}
	
	
	@Transactional
	@Override
	public Apartment modify(Apartment c, long id) {
		// TODO Auto-generated method stub
		apartmentDAO.saveOrUpdate(c);
		return apartmentDAO.get(id);
	}
	
	@Transactional
	@Override
	public List<House> getSpHouse(Long start, Long end, Long id) {
		// TODO Auto-generated method stub
		String hqlString = "from House where apartmentId=? and date >=? and date <=?";
		Object[] values = { id, start, end };
		return houseDAO.getListByHQL(hqlString, values);
	}

	@Override
	public double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		// TODO Auto-generated method stub
		 double radLat1 = rad(lat1);  
	       double radLat2 = rad(lat2);  
	       double a = radLat1 - radLat2;      
	       double b = rad(lng1) - rad(lng2);  
	       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
	        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
	       s = s * EARTH_RADIUS;  
	       s = Math.round(s * 1000);  
	       return s;
	}

	@Transactional
	@Override
	public List<Apartment> landlord(Long affiliation) {
		String hqlString = "from Apartment where affiliation=?";
		Object[] values = {affiliation};
		return apartmentDAO.getListByHQL(hqlString, values);
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		System.out.println(cal.getTime());
		Date d =cal.getTime(); 
		System.out.println(d.getTime());	
		System.out.println(sd.format(d.getTime()+1000*60*60*24));
		System.out.println(sd.format(DateUtil.getStartTime()));
		System.out.println(sd.format(cal.getTime()));
	}
}
