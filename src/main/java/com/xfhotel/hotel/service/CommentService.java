package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.support.PageResults;

public interface CommentService extends BaseService<Comment>{
	public List<Comment> getCommentsByRoom(Long roomId);
	public PageResults<Comment> getComments(Long roomId,int page);
}
