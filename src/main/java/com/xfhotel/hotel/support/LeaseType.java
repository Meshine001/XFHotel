package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

public class LeaseType {
	private int typeId;
	private String type;
	private static List<LeaseType> leaseTypes;
	public LeaseType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static List<LeaseType> getLeaseTypes() {
		if(leaseTypes == null){
			leaseTypes = new ArrayList<LeaseType>();
			leaseTypes.add(new LeaseType(0, "全部"));
			leaseTypes.add(new LeaseType(1, "整租"));
			leaseTypes.add(new LeaseType(2, "合租"));
		}
		return leaseTypes;
	}
	
}
