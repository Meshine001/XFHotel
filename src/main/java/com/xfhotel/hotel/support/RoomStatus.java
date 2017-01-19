package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

public class RoomStatus {
	private int statusId;
	private String status;
	private static List<RoomStatus> statusArray;
	public RoomStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static List<RoomStatus> getStatusArray() {
		if(statusArray == null){
			statusArray = new ArrayList<RoomStatus>();
			statusArray.add(new RoomStatus(0, "不限"));
			statusArray.add(new RoomStatus(1, "立即入住"));
			statusArray.add(new RoomStatus(2, "一周内入住"));
			statusArray.add(new RoomStatus(3, "两周内入住"));
			
		}
		return statusArray;
	}
	
}
