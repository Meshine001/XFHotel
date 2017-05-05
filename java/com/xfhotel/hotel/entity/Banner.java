package com.xfhotel.hotel.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_banner")
public class Banner implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String pic;
	private String title;
	private String linkUrl;
	private boolean showHome;
	
	
	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public boolean isShowHome() {
		return showHome;
	}

	public void setShowHome(boolean showHome) {
		this.showHome = showHome;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", pic=" + pic + ", title=" + title + ", linkUrl=" + linkUrl + ", showHome="
				+ showHome + "]";
	}


	
	
}
