package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 优惠券
 * @author Ming
 *
 */
@Entity
@Table(name = "t_coupon")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long startTime;
	private Long endTime;
	private int type;
	private String cValue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getcValue() {
		return cValue;
	}
	public void setcValue(String cValue) {
		this.cValue = cValue;
	}
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", type=" + type + ", cValue="
				+ cValue + "]";
	}
	
	
}
