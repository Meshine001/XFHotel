package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

import com.xfhotel.hotel.entity.Apartment;

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
			for(int i=0; i<Apartment.TYPE_NUM;i++){
				leaseTypes.add(new LeaseType(i, Apartment.getTypeDescription(i)));
			}
		}
		return leaseTypes;
	}
	
}
