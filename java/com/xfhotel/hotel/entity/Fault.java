package com.xfhotel.hotel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.StringSplitUtil;

@Entity
@Table(name = "t_fault")
public class Fault {
	public final static String getTypeFaultItem(int[] faultItem1){
		List<String> list = new ArrayList<String>();
		for(int faultItem: faultItem1){
			switch(faultItem){
			case 0:
				list.add("水电");
				break;
			case 1:
				list.add("电器");
				break;
			case 2:
				list.add("家具");
				break;
			}
		}
	        String[] arr = new String[list.size()];    
	        list.toArray(arr); 
		String faultItem= StringSplitUtil.buildStrGroup(arr);
		return faultItem;
		
	}
	public final static String getmaintainTime(int maintainTime){
		switch(maintainTime){
		case 0:
			return "当天随时 ";
		case 1:
			return "2小时内 ";
		case 2:
			return "2小时后";
		default:
			return "当天随时";
		}
	}
	public final static int STATUS_NOT_AFFIRM = 0;//等待管理员呼叫维修
	public final static int STATUS_CONDUCT = 1;//正在维修
	public final static int STATUS_COMPLETE = 2;//完成
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long roomId;//房间id	
	private Long oederId;//订单id
	private int status;// 状态
	private Long time;//下单时间
	private String faultItem;//保洁项目
	private String maintainTime;//打扫时间
	private String demand;//其他需求
	
	public Fault() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getOederId() {
		return oederId;
	}

	public void setOederId(Long oederId) {
		this.oederId = oederId;
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


	public String getFaultItem() {
		return faultItem;
	}
	public void setFaultItem(String faultItem) {
		this.faultItem = faultItem;
	}
	public String getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
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
		info.put("roomId", roomId);
		info.put("oederId", oederId);
		info.put("status", getStatusString(status));
		info.put("time", DateUtil.format(new Date(time), "yyyy-MM-dd"));
		info.put("faultItem", faultItem);
		info.put("maintainTime", maintainTime);
		info.put("demand", demand);
		return info;
	}
	String getStatusString(int status) {
		switch (status) {
		case STATUS_NOT_AFFIRM:
			return "等待管理员呼叫维修";
		case STATUS_CONDUCT:
			return "正在维修";
		case STATUS_COMPLETE:
			return "完成";
		}
		return "已完成";
	}
}
