package com.xfhotel.hotel.service;

import java.util.List;
import java.util.Map;

import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.support.PageResults;

public interface CommentService extends BaseService<Comment>{
	public List<Comment> getCommentsByRoom(Long roomId);
	public PageResults<Comment> getComments(Long roomId,int page);
	public Map<String, Object> getRoomRates(Long roomId);
}
