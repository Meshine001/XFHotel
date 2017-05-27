package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.chainsaw.Main;

import com.xfhotel.hotel.support.StringSplitUtil;

/**
 * 保洁
 * @author Jing
 *
 */
@Entity
@Table(name = "t_clean")
public class Clean {
	
	public final static String getTypeDescription(int[] content1){
		String[] s = new String[4];
		for(int content: content1){
			switch(content){
			case 0:
				s[content] ="地面卫生打扫 ";
				break;
			case 1:
				s[content] ="清理室内垃圾";
				break;
			case 2:
				s[content] ="换洗床上用品";

				break;
			case 3:
				s[content] = "室内全面保洁";
				
				break;
				
			}
		}
		String content= StringSplitUtil.buildStrGroup(s);
		return content;
		
	}
	public final static String getcleanTime(int cleanTime){
		switch(cleanTime){
		case 0:
			return "2小时内 ";
		case 1:
			return "2小时后";
		case 2:
			return "当天随时";
		default:
			return "当天随时";
		}
	}
	public final static int STATUS_NOT_AFFIRM = 0;//等待管理员呼叫保洁
	public final static int STATUS_CONDUCT = 1;//正在清扫
	public final static int STATUS_COMPLETE = 2;//完成
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long roomId;//房间id	
	private Long oederId;//订单id
	private int status;// 状态
	private Long time;//下单时间
	private String content;//保洁项目
	private String cleanTime;//打扫时间
	private String demand;//其他需求
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getCleanTime() {
		return cleanTime;
	}

	public void setCleanTime(String cleanTime) {
		this.cleanTime = cleanTime;
	}

	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	
	public Clean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Clean [id=" + id + ", roomId=" + roomId + ", oederId=" + oederId + ", status=" + status + ", time="
				+ time +",content="+content +",cleanTime="+ cleanTime+",demand="+ demand+ "]";
	}
	String getStatusString(int status) {
		switch (status) {
		case STATUS_NOT_AFFIRM:
			return "等待管理员呼叫保洁";
		case STATUS_CONDUCT:
			return "正在清扫";
		case STATUS_COMPLETE:
			return "完成";
	
		}
		return "已完成";
	}
	public static void main(String[] args) {
		int i = 2;
		System.out.println(getcleanTime(i));
	}
}


