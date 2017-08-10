package com.xfhotel.hotel.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfhotel.hotel.support.DateUtil;

@Entity
@Table(name = "t_triporder")
public class TripOrder {
	public final static int STATUS_ON_PAY = 0;//未支付
	public final static int STATUS_ON_LEASE = 1;//进行中
	public final static int STATUS_COMPLETE = 2;//完成订单
	public final static int STATUS_TIME_OUT = 3;//超时
	public final static int STATUS_CHARGEBACK = 4;//退款
	public final static int STATUS_ON_COMFIRM = 5;//需要管理员确认
	public final static int STATUS_ON_OUT_LEASE = 6;//
	
	public final static String PAY_PLATFORM_WECHAT_NATIVE = "微信扫码";
	public final static String PAY_PLATFORM_WECHAT_JSAPI = "微信公共号";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roomName;//房间名
	private String tripId;//车名
	private Long startTime;
	private Long endTime;
	private Double price;
	private Long tel;
	private Long OederId;
	private int status;
	private Long time;
	private Long cusId;
	 private String payPlatform;//支付平台
	private String payNo;
	private String classify;
	private String demand;//其他需求
	
	public TripOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getTel() {
		return tel;
	}
	public void setTel(Long tel) {
		this.tel = tel;
	}
	public Long getOederId() {
		return OederId;
	}
	public void setOederId(Long oederId) {
		OederId = oederId;
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
	public Long getCusId() {
		return cusId;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
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
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public Map toMap(){
		Map info = new HashMap();
		info.put("id", id);
		info.put("roomName", roomName);
		info.put("tripId", tripId);
		info.put("startTime",DateUtil.format(new Date(startTime), "yyyy-MM-dd HH:mm:ss"));
		info.put("endTime", DateUtil.format(new Date(endTime), "yyyy-MM-dd HH:mm:ss"));
		info.put("price", price);
		info.put("time",   DateUtil.format(new Date(time), "yyyy-MM-dd HH:mm:ss"));
		info.put("classify", classify);
		info.put("demand", demand);
		info.put("tel", tel);
		info.put("status", getStatusString(status));
		info.put("payPlatform", payPlatform);
		return info;
	}
	String getStatusString(int status) {
		switch (status) {
		case STATUS_ON_PAY:
			return "未支付";
		case STATUS_ON_LEASE:
			return "进行中";
		case STATUS_COMPLETE:
			return "已完成";
		case STATUS_TIME_OUT:
			return "订单超时";
		case STATUS_CHARGEBACK:
			return "退款";
		case STATUS_ON_COMFIRM:
			return "确认中";
		case STATUS_ON_OUT_LEASE:
			return "退定服务确认中";
		}
		return "已完成";
	}
}
