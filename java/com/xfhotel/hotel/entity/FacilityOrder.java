package com.xfhotel.hotel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.StringSplitUtil;

@Entity
@Table(name = "t_facilityorder")
public class FacilityOrder {
	public final static int STATUS_NOT_AFFIRM = 0;//等待管理员添加
	public final static int STATUS_CONDUCT = 1;//正在路上
	public final static int STATUS_COMPLETE = 2;//完成
	public final static int STATUS_ON_PAY = 3;//等待支付
	public final static int STATUS_TIME_OUT = 4;//超时
	
	public final static String PAY_PLATFORM_WECHAT_NATIVE = "微信扫码";
	public final static String PAY_PLATFORM_WECHAT_JSAPI = "微信公共号";
	public final static String getmaintainTime(int addTime){
		switch(addTime){
		case 0:
			return "当天随时 ";
		case 1:
			return "2小时内 ";
		case 2:
			return "2小时后";
		default:
			return "当天随时";
		}
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roomId;//房间名	
	private Long oederId;//订单id
	private int status;// 状态
	private Long time;//下单时间
	private String Facility;//设施
	private String addTime;//添加时间
	private String demand;//其他需求
	private double price;//价格
	private String classify;//类型
	private Long fate;//天数
	private String payPlatform;//支付平台
	private String payNo;//订单号
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public Long getOederId() {
		return oederId;
	}
	public void setOederId(Long oederId) {
		this.oederId = oederId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getFacility() {
		return Facility;
	}
	public void setFacility(String facility) {
		Facility = facility;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	public Long getFate() {
		return fate;
	}
	public void setFate(Long fate) {
		this.fate = fate;
	}
	
	public String getPayPlatform() {
		return payPlatform;
	}
	public void setPayPlatform(String payPlatform) {
		this.payPlatform = payPlatform;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public Map toMap(){
		Map info = new HashMap();
		info.put("id", id);
		info.put("roomId", roomId);
		info.put("oederId", oederId);
		info.put("status", getStatusString(status));
		info.put("time", DateUtil.format(new Date(time), "yyyy-MM-dd HH:mm:ss"));
		info.put("price", price);
		info.put("addTime",  addTime);
		info.put("classify", classify);
		info.put("demand", demand);
		info.put("Facility", Facility);
		info.put("fate", fate);
		return info;
	}
	String getStatusString(int status) {
		switch (status) {
		case STATUS_NOT_AFFIRM:
			return "等待管理员确认";
		case STATUS_CONDUCT:
			return "正在路上";
		case STATUS_COMPLETE:
			return "完成";
		case STATUS_ON_PAY:
			return "等待支付";
		case STATUS_TIME_OUT:
			return "订单超时";
		}
		return "已完成";
	}
}
