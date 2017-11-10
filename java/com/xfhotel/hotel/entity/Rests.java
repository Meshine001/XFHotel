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
@Table(name = "t_rests")
public class Rests {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roomName;//房间名称
	private Long apId;//房间id	
	private String source;//来源
	private String name;//姓名
	private int tel;// 电话
	private int fate;//天数
	private String time;//下单时间
	private String startTime;//开始时间
	private String endTime;//结束时间
	private Double sum;//金额
	public Rests() {
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
	public Long getApId() {
		return apId;
	}
	public void setApId(Long apId) {
		this.apId = apId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public int getFate() {
		return fate;
	}
	public void setFate(int fate) {
		this.fate = fate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public Map toMap(){
		Map map = new HashMap();
		map.put("id", id);
		map.put("roomName", roomName);
		map.put("apId", apId);
		map.put("source", source);
		map.put("name", name);
		map.put("tel", tel);
		map.put("fate", fate);
		map.put("time",  DateUtil.format(new Date(time), "yyyy-MM-dd"));
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("sum", sum);
		return map;
	}
	
}
