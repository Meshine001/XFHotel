package com.xfhotel.hotel.support;

import java.io.Serializable;

public class Message implements Serializable{
	
	public Message(int statusCode, Object content) {
		super();
		this.statusCode = statusCode;
		this.content = content;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * statusCode = 0 异常
	 * statusCode = 1 正常
	 * 
	 */
	private int statusCode;
	private Object content;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [statusCode=" + statusCode + ", content=" + content + "]";
	}
	
	/**
	 * 获取返回消息
	 * @param statusCode
	 * @param content
	 * @return
	 */
	public static Message getMessage(int statusCode,Object content){
		Message msg = new Message(statusCode, content);
		return msg;
	}
}
