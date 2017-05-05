package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

public class Area {
	private int areaId;
	private String areaName;
	private static List<Area> areas;
	
	public Area() {
	
	}
	
	
	
	public Area(int areaId, String areaName) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
	}



	public int getAreaId() {
		return areaId;
	}



	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}



	public String getAreaName() {
		return areaName;
	}



	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



	public static List<Area> getAreas(){
		if(areas == null){
			areas = new ArrayList<Area>();
			areas.add(new Area(0,"不限"));
			areas.add(new Area(1,"莲湖区"));
			areas.add(new Area(2,"新城区"));
			areas.add(new Area(3,"碑林区"));
			areas.add(new Area(4,"灞桥区"));
			areas.add(new Area(5,"未央区"));
			areas.add(new Area(6,"雁塔区"));
			areas.add(new Area(7,"阎良区"));
			areas.add(new Area(8,"临潼区"));
			areas.add(new Area(9,"长安区"));
		}
		return areas;
	}
	

	
	public static String getAreaById(Integer id){
		return Area.getAreas().get(id).getAreaName();
	}
	
	public static void main(String[] args) {
		System.out.println(Area.getAreaById(1));
	}

}
