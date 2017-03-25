package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CommentDAOImpl;
import com.xfhotel.hotel.entity.Comment;
import com.xfhotel.hotel.support.PageResults;

@Service
public class CommentService implements com.xfhotel.hotel.service.CommentService {
	
	@Autowired
	CommentDAOImpl commentDAO;

	@Transactional
	@Override
	public void add(Comment t) {
		commentDAO.save(t);
	}

	@Transactional
	@Override
	public void delete(Comment t) {
		commentDAO.delete(t);
	}

	@Transactional
	@Override
	public void update(Comment t) {
		commentDAO.update(t);
	}

	@Transactional
	@Override
	public List<Comment> list() {
		return commentDAO.getListByHQL("from Comment", null);
	}
	
	@Transactional
	@Override
	public List<Comment> getCommentsByRoom(Long roomId) {
		String hql = "from Comment where roomId=?";
		Object[] v = {roomId};
		return commentDAO.getListByHQL(hql, v);
	}

	@Transactional
	@Override
	public PageResults<Comment> getComments(Long roomId, int page) {
		StringBuffer s = new StringBuffer();
		s.append("from Comment comment where comment.roomId=");
		s.append(roomId).append("order by comment.time desc");
		StringBuffer s1 = new StringBuffer();
		s1.append("select count(*) from Comment comment where comment.roomId=").append(roomId);
		return commentDAO.findPageByFetchedHql(s.toString(), s1.toString(), page, 5,null);
	}

}
