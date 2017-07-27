package com.xfhotel.hotel.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 
@Entity
@Table(name="t_leaveword")
public class Leaveword implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String content;
    private String name;
    private String reply;
    @Id
    @Basic(optional=false)
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name="Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name="Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

@Column(name="reply")
public String getReply() {
    return reply;
}

public void setReply(String reply) {
    this.reply = reply;
	}
}
 
