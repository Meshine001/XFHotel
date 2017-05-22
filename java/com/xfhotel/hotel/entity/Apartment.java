 package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Entity
@Table(name = "t_apartment")
public class Apartment {
	
	public final static int TYPE_NUM = 4;
	public final static int TYPE_ALL = 0;
	public final static int TYPE_HOTEL = 1;
	public final static int TYPE_APARTMENT = 2;
	public final static int TYPE_PLAY_ROOM = 3;
	
	
	
	@Id
	@GeneratedValue(generator = "apartmentgenerator")
	@GenericGenerator(name = "apartmentgenerator", strategy = "increment")
	private long id;
	
	private String basic_info;//基本信息
	
	private String position;//位置信息
	
	private String description;//综合描述
	
	private String te_se;
	private String jia_ju;
	private String wei_yu;
	private String can_chu;
	private String pei_tao;
	private String zou_bian;
	private String qi_ta;
	
	
	private String hu_xing_tu;
	private String fang_jian_tu;
	private String xiao_qu_tu;
	
	private boolean show_home;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JSONObject getBasic_info() {
		return JSONObject.fromObject(basic_info);
	}

	public void setBasic_info(String basic_info) {
		this.basic_info = basic_info;
	}

	

	public JSONArray getTe_se() {
		return JSONArray.fromObject(te_se);
	}

	public void setTe_se(String te_se) {
		this.te_se = te_se;
	}

	public JSONArray getJia_ju() {
		return JSONArray.fromObject(jia_ju);
	}

	public void setJia_ju(String jia_ju) {
		this.jia_ju = jia_ju;
	}

	public JSONArray getWei_yu() {
		return JSONArray.fromObject(wei_yu);
	}

	public void setWei_yu(String wei_yu) {
		this.wei_yu = wei_yu;
	}

	public JSONArray getCan_chu() {
		return JSONArray.fromObject(can_chu);
	}

	public void setCan_chu(String can_chu) {
		this.can_chu = can_chu;
	}

	public JSONArray getPei_tao() {
		return JSONArray.fromObject(pei_tao);
	}

	public void setPei_tao(String pei_tao) {
		this.pei_tao = pei_tao;
	}

	public JSONArray getZou_bian() {
		return JSONArray.fromObject(zou_bian);
	}

	public void setZou_bian(String zou_bian) {
		this.zou_bian = zou_bian;
	}

	public JSONArray getQi_ta() {
		return JSONArray.fromObject(qi_ta);
	}

	public void setQi_ta(String qi_ta) {
		this.qi_ta = qi_ta;
	}

	

	public String getHu_xing_tu() {
		return hu_xing_tu;
	}

	public void setHu_xing_tu(String hu_xing_tu) {
		this.hu_xing_tu = hu_xing_tu;
	}

	public JSONArray getFang_jian_tu() {
		return JSONArray.fromObject(fang_jian_tu);
	}

	public void setFang_jian_tu(String fang_jian_tu) {
		this.fang_jian_tu = fang_jian_tu;
	}

	public JSONArray getXiao_qu_tu() {
		return JSONArray.fromObject(xiao_qu_tu);
	}

	public void setXiao_qu_tu(String xiao_qu_tu) {
		this.xiao_qu_tu = xiao_qu_tu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public JSONObject getPosition() {
		return JSONObject.fromObject(position);
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isShow_home() {
		return show_home;
	}

	public void setShow_home(boolean show_home) {
		this.show_home = show_home;
	}

	public JSONObject toJson(){
		return JSONObject.fromObject(this);
	}
	
	public final static String getTypeDescription(int type){
		switch(type){
		case 0:
			return "不限";
		case 1:
			return "酒店型";
		case 2:
			return "公寓型";
		case 3:
			return "休闲型";
		default:
			return "不限";
		}
	}
	
	public final static int getTypeCode(String type){
		if(type.equals("不限"))return 0;
		if(type.equals("酒店型"))return 1;
		if(type.equals("公寓型"))return 2;
		if(type.equals("休闲型"))return 3;
		return 0;

	}

}
