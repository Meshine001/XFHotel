package com.xfhotel.hotel.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.FacilityService;
import com.xfhotel.hotel.service.FeatureService;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/apartment")
public class ApartmentController {

	@Autowired
	ApartmentService apartmentService;
	@Autowired
	FacilityService facilityService;
	@Autowired
	RoomService roomService;
	@Autowired
	FeatureService featureService;

	@Autowired
	FileService fileService;
	@Autowired
	HttpSession session;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public String apartmentList() {
		session.setAttribute("apartments", apartmentService.list());
		return "/admin/apartment/list";
	}


	/**
	 * 跳转添加公寓页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		
		return "/admin/apartment/add";
	}


	/**
	 * 添加公寓
	 * @param jing_du
	 * @param wei_du
	 * @param bd_wei_zhi
	 * @param xa_wei_zhi
	 * @param jie_dao
	 * @param xiao_qu
	 * @param lou_hao
	 * @param dan_yuan
	 * @param lou_ceng
	 * @param zong_lou_ceng
	 * @param men_pai
	 * @param suo_di_zhi
	 * @param cao_xiang
	 * @param mian_ji
	 * @param shi
	 * @param ting
	 * @param wei
	 * @param yang_tai
	 * @param reng_shu
	 * @param chuang
	 * @param miao_su
	 * @param te_se
	 * @param jia_ju
	 * @param wei_yu
	 * @param can_chu
	 * @param pei_tao
	 * @param zou_bian
	 * @param qi_ta
	 * @param pic1
	 * @param pic2
	 * @param pic3
	 * @param lei_xing
	 * @param jia_ge
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se, String jia_ju, String wei_yu, String can_chu,
			String pei_tao, String zou_bian, String qi_ta, String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge) {

		Apartment apartment = apartmentService.add(jing_du, wei_du, bd_wei_zhi, xa_wei_zhi, jie_dao, xiao_qu, lou_hao,
				dan_yuan, lou_ceng, zong_lou_ceng, men_pai, suo_di_zhi, cao_xiang, mian_ji, shi, ting, wei, yang_tai,
				reng_shu, chuang, miao_su, te_se, jia_ju, wei_yu, can_chu, pei_tao, zou_bian, qi_ta, pic1, pic2, pic3,
				lei_xing, jia_ge);
		
		return "redirect:/admin/apartment/update/" + apartment.getId();
	};

	/**
	 * 获取某公寓详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public JSONObject get(Long id){
		return apartmentService.getApartmentById(id);
	}
	

	/**
	 * 设置公寓首页显示
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showHome/{id}", method = RequestMethod.GET)
	public String showHome(@PathVariable("id")Long id){
		Apartment apartment = apartmentService.findById(id);
		apartment.setShow_home(apartment.isShow_home()?false:true);
		apartmentService.update(apartment);
		return "redirect:/admin/apartment";
	}
	
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Long id, String jing_du, String wei_du, String bd_wei_zhi, String xa_wei_zhi, String jie_dao,
			String xiao_qu, String lou_hao, String dan_yuan, String lou_ceng, String zong_lou_ceng, String men_pai,
			String suo_di_zhi, String cao_xiang, String mian_ji, String shi, String ting, String wei, String yang_tai,
			String reng_shu, String chuang, String miao_su, String te_se, String jia_ju, String wei_yu, String can_chu,
			String pei_tao, String zou_bian, String qi_ta, String pic1, String[] pic2, String[] pic3, String lei_xing,
			String jia_ge) {

    	Apartment apartment = apartmentService.update(id, jing_du, wei_du, bd_wei_zhi, xa_wei_zhi, jie_dao, xiao_qu, lou_hao, dan_yuan, lou_ceng, zong_lou_ceng, men_pai, suo_di_zhi, cao_xiang, mian_ji, shi, ting, wei, yang_tai, reng_shu, chuang, miao_su, te_se, jia_ju, wei_yu, can_chu, pei_tao, zou_bian, qi_ta, pic1, pic2, pic3, lei_xing, jia_ge);

		return "redirect:/admin/apartment/update/" + apartment.getId();
	}

	String formatJSONArrayString(JSONArray ja){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<ja.size();i++){
			sb.append(ja.get(i));
			if(i!=ja.size()-1)
			sb.append("，");
		}
		return sb.toString().trim();
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updatePage(@PathVariable("id") Long id) {
		Apartment apartment = apartmentService.findById(id);
		session.setAttribute("apartment", apartment);
		session.setAttribute("te_se", formatJSONArrayString(apartment.getTe_se()));
		session.setAttribute("jia_ju", formatJSONArrayString(apartment.getJia_ju()));
		session.setAttribute("wei_yu", formatJSONArrayString(apartment.getWei_yu()));
		session.setAttribute("can_chu", formatJSONArrayString(apartment.getCan_chu()));
		session.setAttribute("pei_tao", formatJSONArrayString(apartment.getPei_tao()));
		session.setAttribute("zou_bian", formatJSONArrayString(apartment.getZou_bian()));
		session.setAttribute("qi_ta", formatJSONArrayString(apartment.getQi_ta()));
		
		return "/admin/apartment/update";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Apartment apartment = apartmentService.findById(id);
		apartmentService.delete(apartment);
		return "forward:/admin/apartment";
	}


	@RequestMapping(value = "/price/set", method = RequestMethod.POST)
	public String priceSet(Long apartmentId, String date, String price) {
		Apartment apartment = apartmentService.findById(apartmentId);
		Price sp = apartmentService.getSpPrice(apartmentId, TimeUtil.getDateLong(date));
		if (sp != null) {
			sp.setPrice(Double.valueOf(price));
		} else {
			sp = new Price(apartmentId, TimeUtil.getDateLong(date), Double.valueOf(price));
		}
		
		apartmentService.setSpPrice(sp);

		return "redirect:/admin/apartment/price/" + apartmentId;
	}

	/**
	 * 设置特殊价格
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/price/{id}", method = RequestMethod.GET)
	public String price(@PathVariable("id") Long id) {
		Apartment apartment = apartmentService.findById(id);
		Long start = TimeUtil.getCurrentDateLong();
		Long end = start + 1000 * 60 * 60 * 25 * 60;// 60天
		List<Price> prices = apartmentService.getSpPrices(start, end, id);
		session.setAttribute("apartment", apartment);
		session.setAttribute("spPrices", prices);
		return "admin/apartment/price";
	}

}
