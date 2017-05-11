package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 优惠券
 * @author Jing
 *
 */
@Entity
@Table(name = "t_coupon")
public class Coupon {
	public final static String getTypeDescription(int type){
		switch(type){
		case 0:
			return "电影票";
		case 1:
			return "优惠劵";
		default:
			return "不限";
		}
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long startTime;
	private Long endTime;
	private int type;
	private Double cValue;
	private Long  uId;
	private String  rule;
	
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getuId(){
		return uId;
	}
	public void setuId(Long uId){
		this.uId = uId;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Double getcValue() {
		return cValue;
	}
	public void setcValue(Double cValue) {
		this.cValue = cValue;
	}
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", type=" + type + ", cValue="
				+ cValue +",uId="+uId +",rule="+ rule+ "]";
	}
	
	
}
