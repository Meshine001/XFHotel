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
@Table(name = "t_apply")
public class Apply {
	public final static int STATUS_NOT_AFFIRM = 0;//等待处理
	public final static int STATUS_CONDUCT = 1;//审核中
	public final static int STATUS_COMPLETE = 2;//审核成功
	public final static int STATUS_DEFEATED = 3;//审核失败
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String site;//地址
	private Long tel;//电话
	private Long time;//时间
	private int state;//审核状态
	private Long uId;//房东ID
	private String name;//真实姓名
	
	public Apply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Long getTel() {
		return tel;
	}
	public void setTel(Long tel) {
		this.tel = tel;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	String getStatusString(int status) {
		switch (status) {
		case STATUS_NOT_AFFIRM:
			return "等待处理";
		case STATUS_CONDUCT:
			return "审核中";
		case STATUS_COMPLETE:
			return "审核成功";
		case STATUS_DEFEATED:
			return "审核失败";
		}
		return "已完成";
	}
	
	public Map toMap(){
		Map info = new HashMap();
		info.put("id", id);
		info.put("site", site);
		info.put("uId", uId);
		info.put("tel", tel);
		info.put("name", name);
		info.put("status", getStatusString(state));
		info.put("time", DateUtil.format(new Date(time), "yyyy-MM-dd HH:mm:ss"));
		return info;
	}
}
