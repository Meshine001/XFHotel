package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getScore() {
		return score;
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

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", roomId=" + roomId + ", toWho=" + toWho + ", fromWho=" + fromWho + ", time="
				+ time + ", score=" + score + ", feel=" + feel + ", pics=" + pics + "]";
	}


	

}
