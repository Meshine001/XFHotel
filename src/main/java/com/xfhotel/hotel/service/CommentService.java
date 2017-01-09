package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Comment;

public interface CommentService extends BaseService<Comment>{
	public List<Comment> getCommentsByRoom(Long roomId);
}
