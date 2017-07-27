package com.xfhotel.hotel.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.TimeUtil;

/**
 * 用户评价
 * 
 * @author Ming
 *
 */
@Entity
@Table(name = "t_comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long roomId;
	private Long toWho;
	private Long fromWho;
	private Long time;
	private String score;
	private String feel;
	private String pics;
	private Long entryTime;
	private boolean hasRead;
	private String reply;
	
	public boolean isHasRead() {
		return hasRead;
	}

	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
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



	public Long getToWho() {
		return toWho;
	}

	public void setToWho(Long toWho) {
		this.toWho = toWho;
	}

	public Long getFromWho() {
		return fromWho;
	}

	public void setFromWho(Long fromWho) {
		this.fromWho = fromWho;
	}

	public String getTime() {
		return DateUtil.format(new Date(time), "yyyy-MM-dd hh:mm:ss");
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String[] getScore() {
		return score.split("@");
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getFeel() {
		return feel;
	}

	public void setFeel(String feel) {
		this.feel = feel;
	}

	public String[] getPics() {
		return pics.split("@");
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEntryTime() {
		return  TimeUtil.getDateStr(entryTime);
	}

	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	
	
	

}
