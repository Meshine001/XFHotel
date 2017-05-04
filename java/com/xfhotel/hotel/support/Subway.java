package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

public class Subway {
	private int subwayId;
	private String subwayName;
	private static List<Subway> subways;
	public Subway(int subwayId, String subwayName) {
		super();
		this.subwayId = subwayId;
		this.subwayName = subwayName;
	}
	public int getSubwayId() {
		return subwayId;
	}
	public void setSubwayId(int subwayId) {
		this.subwayId = subwayId;
	}
	public String getSubwayName() {
		return subwayName;
	}
	public void setSubwayName(String subwayName) {
		this.subwayName = subwayName;
	}
	
	public static List<Subway> getSubways(){
		if(subways == null){
			subways = new ArrayList<Subway>();
			subways.add(new Subway(0, "全部"));
			subways.add(new Subway(1, "地铁1号线"));
			subways.add(new Subway(2, "地铁2号线"));
			subways.add(new Subway(3, "地铁3号线"));
		}
		
		return subways;
	}
	
}
