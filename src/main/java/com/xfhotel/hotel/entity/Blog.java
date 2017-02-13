package com.xfhotel.hotel.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.xfhotel.hotel.support.DateUtil;

@Entity
@Table(name = "t_blog")
public class Blog {
	@Id
	@GeneratedValue(generator = "bloggenerator")
	@GenericGenerator(name = "bloggenerator", strategy = "increment")
	private long id;
	private String title;
	private Long date;
	private String path;
	private long authorid;
	private String author;
	private String status;
	
	
	public Blog(String title, Long date, String path, long authorid, String author, String status) {
		super();
		this.title = title;
		this.date = date;
		this.path = path;
		this.authorid = authorid;
		this.author = author;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Blog() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getAuthorid() {
		return authorid;
	}
	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Map toMap(){
		Map map = new HashMap();
		Date d = new Date(date);
		map.put("id", id);
		map.put("title", title);
		map.put("date", DateUtil.format(d));
		map.put("path", path);
		map.put("status", status);
		map.put("authorid", authorid);
		map.put("author", author);
		return map;
	}
}
